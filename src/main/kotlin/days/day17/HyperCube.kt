package days.day17

import kotlin.math.absoluteValue

class HyperCube (
    val x:Int,
    val y:Int,
    val z:Int,
    val w:Int,
    var isActive:Boolean = false
){

    fun isNeighbour(other: HyperCube): Boolean {
        return (x - other.x).absoluteValue <= 1 &&
                (y - other.y).absoluteValue <= 1 &&
                (z - other.z).absoluteValue <= 1 &&
                (w - other.w).absoluteValue <= 1 &&
                !(x == other.x && y == other.y && z == other.z && w == other.w)
    }

    fun getAllNeighbours(): List<HyperCube> {
        val neighbours = mutableSetOf<HyperCube>()
        for (x in -1..1) {
            for (y in -1..1) {
                for (z in -1..1) {
                    for (w in -1..1){
                        neighbours.add(HyperCube(this.x + x, this.y + y, this.z + z, this.w + w))
                    }
                }
            }
        }
        neighbours.remove(this)
        return neighbours.toList()
    }


    override fun toString(): String {
        return "HyperCube(x=$x, y=$y, z=$z, w=$w, isActive=$isActive)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HyperCube

        if (x != other.x) return false
        if (y != other.y) return false
        if (z != other.z) return false
        if (w != other.w) return false
        if (isActive != other.isActive) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        result = 31 * result + z
        result = 31 * result + w
        result = 31 * result + isActive.hashCode()
        return result
    }


}