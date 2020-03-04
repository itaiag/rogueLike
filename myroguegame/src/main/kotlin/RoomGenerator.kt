import kotlin.random.Random

class RoomGenerator(val maxWidth: Int, val maxHeight: Int) {

    private val minWidthAndHeight = 5

    private val defaultNumOfDoors = 1

    private var numOfDoors: Int = defaultNumOfDoors;

    fun generate(roomNumber: Int): List<Room> {
        val rooms = ArrayList<Room>()
        for (i in 1..roomNumber) {
            val room = Room(Dimension(Random.nextInt(minWidthAndHeight, maxWidth), Random.nextInt(minWidthAndHeight, maxHeight)))
            addDoors(room)
            rooms.add(room)
        }
        return rooms
    }

    fun withDoors(numOfDoors : Int) : RoomGenerator {
        this.numOfDoors = numOfDoors
        return this
    }


    fun addDoors(room: Room) {
        var directions = arrayListOf("WEST", "NORTH", "EAST", "SOUTH")
        for (i in 0..numOfDoors) {
            var coordList = ArrayList<Coordination>();
            when (directions.removeAt(Random.nextInt(directions.size))) {
                "WEST" -> for (y in 1 until room.size.height) coordList.add(Coordination(0, y))
                "EAST" -> for (y in 1 until room.size.height) coordList.add(Coordination(room.size.height, y))
                "NORTH" -> for (x in 1 until (room.size.width - 1)) coordList.add(Coordination(x, 0))
                "SOUTH" -> for (x in 1 until (room.size.width - 1)) coordList.add(Coordination(x, room.size.width ))
            }
            val coords = coordList.get(Random.nextInt(coordList.size))
            room.addDoor(Door(coords))
        }

    }

}

