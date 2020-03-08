package rogue

import kotlin.random.Random


fun main(args: Array<String>) {
    val size = Dimension(40, 20)
    val rooms = RoomGenerator(10, 5).generate(Random.nextInt(1, 2))
    val newRooms = RoomPlacer(rooms, size).build()
    MazePainter(size).paint(newRooms)
    newRooms.forEach {
        println(it)

    }
    println("Assigned ${newRooms.size} rooms")
}


