import kotlin.random.Random

fun main() {
    val rooms = RoomGenerator(15,15).generate(Random.nextInt(5,40))
    val newRooms = RoomPlacer(rooms, Dimension(100, 100)).build()
    var roomCounter = 0
    newRooms.forEach {
        if (it.coordination?.x != null){
            roomCounter++
            println(it)
        }

    }
    println("Assigned ${roomCounter} rooms")
}
