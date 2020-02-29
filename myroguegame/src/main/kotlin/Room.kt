class Room( var size : Dimension) {

    val doors : ArrayList<Door> = ArrayList<Door>()
        get() = field

    var coordination : Coordination? = null;

    override fun toString(): String {
        return "${coordination?.x},${coordination?.y}: Width ${size.width}, Length ${size.height} with ${doors.size} doors"
    }

    fun addDoor(door : Door) {
        doors.add(door)
    }

}

class Door(val location : Coordination)