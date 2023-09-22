package days.day1

import days.Day

class Day3:Day() {
    override fun partOne(): Any {
        val input = readInput()
        var result = 0

        var x = 0;
        for (line in input) {
            if (line[x] == '#') {
                result++
            }
            x+=3
            x %= line.length
        }
        return result
    }

    override fun partTwo(): Any {
        TODO("Not yet implemented")
    }
}