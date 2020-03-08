package rogue

import java.lang.IllegalStateException

class Grid<T>(private var size: Dimension, private var defaultVal: T) : Iterator<Coordination> {

    private var currentCoords: Coordination = Coordination(-1, 0)


    override fun hasNext(): Boolean {
        return (currentCoords.x < size.width - 1 || currentCoords.y < size.height - 1)
    }

    override fun next(): Coordination {
        if (currentCoords.x == size.width && currentCoords.y == size.height) {
            throw IllegalStateException("Can't go further")
        }
        if (currentCoords.x < size.width - 1) {
            currentCoords = Coordination(currentCoords.x + 1, currentCoords.y)
        } else {
            currentCoords = Coordination(0, currentCoords.y + 1)
        }
        return currentCoords
    }

    fun resetCurrentCoords() {
        currentCoords = Coordination(0, 0)
    }


    private var arr = arrayOf<Array<Any?>>()

    init {
        for (y in 0 until size.height) {
            var array = arrayOf<Any?>()
            for (x in 0 until size.width) {
                array += defaultVal
            }
            this.arr += array
        }
    }

    fun set(x: Int, y: Int, value: T) {
        arr[x][y] = value
    }

    fun get(coords: Coordination): T {
        return get(coords.x, coords.y) as T
    }

    fun get(x: Int, y: Int): T {
        return arr[x][y] as T
    }

    fun isAvailable(coords: Coordination, occSize: Dimension, occValue: T): Boolean {
        for (y in coords.y until (coords.y + occSize.height)) {
            for (x in coords.x until (coords.x + occSize.width)) {
                if (x >= size.width || y >= size.height) {
                    return false
                }
                if (arr[x][y] == occValue) {
                    return false
                }
            }
        }
        return true
    }

    fun occupy(coords: Coordination, size: Dimension, value: T) {
        for (x in coords.x..(coords.x + size.width - 1)) {
            for (y in coords.y..(coords.y + size.height - 1)) {
                arr[x][y] = value as T
            }
        }
    }


    fun print(printer: GridPrinter<T>) {
        for (y in 0 until size.height) {
            for (x in 0 until size.width) {
                printer.print(arr[x][y] as T)
            }
            printer.newLine()
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