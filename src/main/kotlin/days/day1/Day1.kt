package days.day1

import days.Day

class Day1: Day() {
    override fun partOne(): Any {
        val lines :List<Int> = readInput().map (Integer::parseInt)
        for (i in lines) {
            for (j in lines) {
                if (i + j == 2020) {
                    return i*j
                }
            }
        }
        return -1
    }

    override fun partTwo(): Any {
        val lines :List<Int> = readInput().map (Integer::parseInt)
        for (i in lines) {
            for (j in lines) {
                for (k in lines) {
                    if (i + j + k== 2020) {
                        return i*j*k
                    }
                }
            }
        }
        return -1
    }
}
