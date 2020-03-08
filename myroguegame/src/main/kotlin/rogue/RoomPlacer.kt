package rogue

import kotlin.random.Random

class RoomPlacer(private val rooms: List<Room>, roomSize: Dimension) {

    var gridAssigner: GridAssigner = GridAssigner(roomSize, 2)
        private set

    fun build(): List<Room> {
        assignCoordinats()
        removeUnassignedRooms()
        return rooms
    }

    private fun removeUnassignedRooms() {
        var unassignedRooms = rooms.filter { r -> r.coordination == null }
        unassignedRooms.forEach{(rooms as ArrayList).remove(it)}
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
            for (x in 0 until gridSize.width) {
                var array = arrayOf<Boolean>()
                for (y in 0 until gridSize.height) {
                    array += false
                }
                this.grid += array
            }
        }

        fun availableCoords(size: Dimension): List<Coordination> {
            var coords = ArrayList<Coordination>()
            for (x0 in 0 until gridSize.width) {
                for (y0 in 0 until gridSize.height) {
                    var availble = true
                    for (x1 in x0 until (x0 + size.width)) {
                        for (y1 in y0 until (y0 + size.height)) {
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

data class Dimension(val width: Int, val height: Int) {

    override fun toString(): String {
        return "Width ${width}, Height ${height}"
    }
}

data class Coordination(val x: Int, val y: Int) {

    override fun toString(): String {
        return return ("${x},${y}")
    }
}


fun main(args : Array<String>){
    var grid = arrayOf<Array<Int>>()
    val gridSize = Dimension(10, 20)
    for (y in 0 until gridSize.height) {
        var array = arrayOf<Int>()
        for (x in 0 until gridSize.width) {
            array += x
        }
        grid += array
    }
    for (y in 0 until gridSize.height) {
        print("${y}:")
        for (x in 0 until gridSize.width) {
            print(grid[y][x])
        }
        println("")
    }

}
