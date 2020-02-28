import kotlin.random.Random

class RoomGenerator(val maxWidth : Int, val maxHeight : Int) {

    private val minWidthAndHeight = 5

    fun generate(roomNumber : Int) : List<Room> {
        val rooms = ArrayList<Room>()
        for (i in 1..roomNumber) {
            val room = Room(Dimension(Random.nextInt(minWidthAndHeight,maxWidth),Random.nextInt(minWidthAndHeight,maxHeight)))
            rooms.add(room)
        }
        return rooms
    }

}