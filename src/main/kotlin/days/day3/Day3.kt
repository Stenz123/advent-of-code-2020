package days.day3

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
        val input = readInput()
        var result:Long = 0


        result+=checkSlope(1,1,input)
        result*=checkSlope(3,1,input)
        result*=checkSlope(5,1,input)
        result*=checkSlope(7,1,input)
        result*=checkSlope(1,2,input)

        return result
    }

    private fun checkSlope(xMod: Int, yMod: Int, input: List<String>): Int {
        var i = 0
        var x = 0
        var result = 0
        while (i < input.size) {
            if (input[i][x] == '#') {
                result++
            }

            x += xMod
            x %= input[i].length
            i += yMod
        }
        return result
    }
}