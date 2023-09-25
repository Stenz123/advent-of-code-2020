package days.day1

import days.Day

class Day5: Day(){
    override fun partOne(): Any {
        val input = readInput().map {
            it.replace('F','0')
            .replace('B','1')
            .replace('R','1')
            .replace('L','0')
        }
        var highestId = -1
        for (line in input) {
            val id = (line.substring(0,7).toInt(2)*8+line.substring(7,10).toInt(2))
            if (id > highestId) {
                highestId=id
            }
        }
        return highestId
    }

    override fun partTwo(): Any {
        val input = readInput().map {
            it.replace('F','0')
                .replace('B','1')
                .replace('R','1')
                .replace('L','0')
        }
        val seatIds = mutableListOf<Int>()
        for (line in input) {
            seatIds.add(line.substring(0,7).toInt(2)*8+line.substring(7,10).toInt(2))
        }
        seatIds.sort()
        for (i in seatIds.indices) {

            if (seatIds[i] != seatIds[i+1] - 1) {
                return seatIds[i]+1
            }
        }

        return -1
    }
}