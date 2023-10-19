package days.day6

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
        val input = readInput()

        val groups = mutableListOf<List<String>>()

        var groupBuilder = mutableListOf<String>()
        for (line in input) {
            if (line.isBlank()) {
                groups.add(groupBuilder)
                groupBuilder = mutableListOf()
            } else {
                groupBuilder.add(line)
            }
        }
        groups.add(groupBuilder)
        var result = 0
        for (group in groups) {
            for (c in group.joinToString(separator = "").toSet()) {
                result++
                group@for (entry in group) {
                    if (!entry.contains(c)) {
                        result--
                        break@group
                    }
                }
            }
        }
        return result
    }
}