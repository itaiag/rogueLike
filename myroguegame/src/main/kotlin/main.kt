import kotlin.random.Random


fun main(args: Array<String>) {
    val size = Dimension(20, 20)
    val rooms = RoomGenerator(10, 10).generate(Random.nextInt(5, 10))
    val newRooms = RoomPlacer(rooms, size).build()
    println("")
    MazePainter(size).paint(newRooms)
    var roomCounter = 0
    newRooms.forEach {
        if (it.coordination?.x != null) {
            roomCounter++
            println(it)
        }

    }
    println("Assigned ${roomCounter} rooms")
}


