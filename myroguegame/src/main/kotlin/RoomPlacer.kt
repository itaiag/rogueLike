import kotlin.random.Random

class RoomPlacer(private val rooms: List<Room>, private val roomSize: Dimension) {

    var gridAssigner: GridAssigner = GridAssigner(roomSize, 2)
        private set

    fun build(): List<Room> {
        assignCoordinats()
        this.gridAssigner.print()
        return rooms
    }

    private fun assignCoordinats() {
        for (room in rooms) {
            var availableCoords = gridAssigner.availableCoords(room.size)
            if (availableCoords.isEmpty()){
                continue
            }
            room.coordination = availableCoords.get(Random.nextInt(availableCoords.size))
            gridAssigner.occupy(room.coordination!!, room.size)
        }
    }


    class GridAssigner(val gridSize: Dimension, val margin: Int) {

        var grid = arrayOf<Array<Boolean>>()

        init {
            for (i in 0..gridSize.width) {
                var array = arrayOf<Boolean>()
                for (j in 0..gridSize.height) {
                    array += false
                }
                this.grid += array
            }
        }

        fun print() {
            for (array in grid) {
                for (value in array) {
                    if (value) {
                        print("#")
                    } else {
                        print(".")
                    }
                }
                println("")
            }
        }

        fun availableCoords(size: Dimension): List<Coordination> {
            var coords = ArrayList<Coordination>()
            for (x0 in 0..gridSize.width) {
                for (y0 in 0..gridSize.height) {
                    var availble: Boolean = true
                    for (x1 in x0..(x0 + size.width)) {
                        for (y1 in y0..(y0 + size.height)) {
                            if (x1 >= gridSize.width - margin || y1 >= gridSize.height - margin
                                    || x1 <= margin || y1 <= margin) {
                                availble = false
                                break
                            }
                            if (grid[x1][y1] || grid[x1 + margin][y1] || grid[x1][y1 + margin] || grid[x1 - margin][y1] || grid[x1][y1 - margin]) {
                                availble = false
                                break
                            }


                        }
                    }
                    if (availble) {
                        coords.add(Coordination(x0, y0))
                    }
                }
            }
            return coords
        }

        fun occupy(coords: Coordination, size: Dimension) {
            for (x in coords.x..(coords.x + size.width)) {
                for (y in coords.y..(coords.y + size.height)) {
                    grid[x][y] = true
                }
            }
        }


    }

}

data class Dimension(val width: Int, val height: Int)

data class Coordination(val x: Int, val y: Int)



