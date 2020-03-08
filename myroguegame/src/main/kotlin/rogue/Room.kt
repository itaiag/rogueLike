package rogue

class Room(var size : Dimension) {

    val doors : ArrayList<Door> = ArrayList<Door>()
        get() = field

    var coordination : Coordination? = null;

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("${coordination?.x},${coordination?.y}: Width ${size.width}, Height ${size.height} with ${doors.size} doors: (")
        doors.forEach{sb.append(it.toString() + ",")}
        sb.append(")")
        return sb.toString()
    }

    fun addDoor(door : Door) {
        doors.add(door)
    }

}

class Door(val location : Coordination) {

    override fun toString() : String {
        return "rogue.Door location ${location}"


    }
}