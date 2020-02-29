class MazePainter(private val mazeSize: Dimension) {

    var grid: Grid = Grid(mazeSize)

    fun paint(rooms: List<Room>) {
        for (room in rooms) {
            val xpos = ((room.coordination?.x) ?: 0)
            val ypos = ((room.coordination?.y) ?: 0)
            for (x in xpos..(xpos + room.size.width)) {
                for (y in ypos..(ypos + room.size.height)) {
                    if (x == xpos || y == ypos || x == xpos + room.size.width || y == ypos + room.size.height) {
                        grid.setTile(x, y, '#')
                    } else {
                        grid.setTile(x, y, ' ')
                    }
                }
            }
            for (door in room.doors) {
                grid.setTile(xpos + door.location.x, ypos + door.location.y, '+')
            }
        }
        for (x in 0..mazeSize.width) {
            for (y in 0..mazeSize.height) {
                print(grid.getTile(x, y))
            }
            println("")
        }


    }

    class Grid(val gridSize: Dimension) {

        var grid = arrayOf<Array<Char>>()

        init {
            for (i in 0..gridSize.width) {
                var array = arrayOf<Char>()
                for (j in 0..gridSize.height) {
                    array += '.'
                }
                this.grid += array
            }
        }

        fun setTile(x: Int, y: Int, tile: Char) {
            grid[x][y] = tile
        }

        fun getTile(x: Int, y: Int): Char {
            return grid[x][y]
        }
    }

}