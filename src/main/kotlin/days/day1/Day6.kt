package days.day1

import days.Day

class Day6:Day() {
    override fun partOne(): Any {
        val input = readInput()

        val groups = mutableListOf<String>()
        var currentEntry = StringBuilder()

        for (line in input) {
            if (line.isBlank()) {
                groups.add(currentEntry.toString().trim())
                currentEntry = StringBuilder()
            } else {
                currentEntry.append(" ").append(line)
            }
        }
        groups.add(currentEntry.toString().trim())
        return groups.map{it.replace(" ", "").toSet().count()}.sum()
    }

    override fun partTwo(): Any {
        TODO("Not yet implemented")
    }
}