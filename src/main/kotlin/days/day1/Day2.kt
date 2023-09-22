package days.day1

import days.Day

class Day2: Day() {
    override fun partOne(): Any {
        val inputs = readInput().map {it.split("[- :]".toRegex())}
        var resultCounter = 0
        for (password in inputs) {
            val charCount = password[4].count { it == password[2][0] }
            if (charCount >= password[0].toInt() && charCount <= password[1].toInt()) {
                resultCounter++
            }
        }
        return resultCounter
    }

    override fun partTwo(): Any {
        TODO()
    }
}