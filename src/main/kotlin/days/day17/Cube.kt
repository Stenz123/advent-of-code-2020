package days.day17

import kotlin.math.absoluteValue

class Cube (
    val x:Int,
    val y:Int,
    val z:Int,
    var isActive:Boolean = false
){

    fun isNeighbour(other: Cube): Boolean {
        return (x - other.x).absoluteValue <= 1 &&
                (y - other.y).absoluteValue <= 1 &&
                (z - other.z).absoluteValue <= 1 &&
                !(x == other.x && y == other.y && z == other.z)
    }

    fun getAllNeighbours(): List<Cube> {
        val neighbours = mutableListOf<Cube>()
        for (x in -1..1) {
            for (y in -1..1) {
                for (z in -1..1) {
                    neighbours.add(Cube(this.x + x, this.y + y, this.z + z))
                }
            }
        }
        neighbours.remove(this)
        return neighbours
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cube

        if (x != other.x) return false
        if (y != other.y) return false
        if (z != other.z) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        result = 31 * result + z
        return result
    }

    override fun toString(): String {
        return "Cube(x=$x, y=$y, z=$z, $isActive)"
    }


}