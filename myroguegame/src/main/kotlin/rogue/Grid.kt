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

    fun isOccupy(coords: Coordination, occSize: Dimension, occValue: T): Boolean {
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


    fun availableCoords(occSize: Dimension, occupy: T, margin: Int): List<Coordination> {
        var coords = ArrayList<Coordination>()
        for (x0 in 0 until size.width) {
            for (y0 in 0 until size.height) {
                var availble = true
                for (x1 in x0 until (x0 + occSize.width)) {
                    for (y1 in y0 until (y0 + occSize.height)) {
                        if (x1 >= size.width - margin || y1 >= size.height - margin
                                || x1 <= margin || y1 <= margin) {
                            availble = false
                            break
                        }
                        if (arr[x1][y1] == occupy as T
                                || arr[x1 + margin][y1] == occupy as T
                                || (arr[x1][y1 + margin]) == occupy as T
                                || arr[x1 - margin][y1] == occupy as T
                                || arr[x1][y1 - margin] == occupy as T) {
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