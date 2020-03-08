package rogue

import kotlin.random.Random

class RoomPlacer(private val rooms: List<Room>, roomSize: Dimension) {

    var grid = Grid<Boolean>(roomSize, false)
        private set

    fun build(): List<Room> {
        assignCoordinats()
        removeUnassignedRooms()
        return rooms
    }

    private fun removeUnassignedRooms() {
        var unassignedRooms = rooms.filter { r -> r.coordination == null }
        unassignedRooms.forEach { (rooms as ArrayList).remove(it) }
    }

    private fun assignCoordinats() {
        for (room in rooms) {
            var availableCoords = calculateAvailableCoords(room.size)
            if (availableCoords.isEmpty()){
                continue
            }
            room.coordination = availableCoords.get(Random.nextInt(availableCoords.size))
            grid.occupy(room.coordination!!, room.size,true)
        }
    }

    private fun calculateAvailableCoords(size: Dimension): List<Coordination> {
        val available = ArrayList<Coordination>()
        grid.resetCurrentCoords()
        while (grid.hasNext()) {
            val coords = grid.next()
            if (grid.isAvailable(coords, size, true)) {
                available.add(coords)
            }
        }
        grid.resetCurrentCoords()
        return available
    }


}




