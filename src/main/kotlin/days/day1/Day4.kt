package days.day1

import days.Day

class Day4: Day() {
    override fun partOne(): Any {
        val input = readInput()

        val passportEntries = mutableListOf<String>()
        var currentEntry = StringBuilder()

        for (line in input) {
            if (line.isBlank()) {
                passportEntries.add(currentEntry.toString().trim())
                currentEntry = StringBuilder()
            } else {
                currentEntry.append(" ").append(line)
            }
        }
        passportEntries.add(currentEntry.toString().trim())

        var result = 0

        for (passport in passportEntries) {
            if (isPassportValid(countSubstrings(passport))) {
                result++
            }
        }
        return result
    }

    override fun partTwo(): Any {
        TODO("Not yet implemented")
    }

    private fun isPassportValid(passport: Map<String, Int>):Boolean {
        if (passport["byr"]!! < 1) return false
        if (passport["iyr"]!! < 1) return false
        if (passport["eyr"]!! < 1) return false
        if (passport["hgt"]!! < 1) return false
        if (passport["hcl"]!! < 1) return false
        if (passport["ecl"]!! < 1) return false
        if (passport["pid"]!! < 1) return false
        if (passport["cid"]!! < 1 && passport["cid"] != 0) return false
        return true
    }

    private fun countSubstrings(input: String): Map<String, Int> {
        val regexStrings = listOf("byr", "iyr","eyr", "hgt", "hcl", "ecl", "pid", "cid")
        val counts = mutableMapOf<String, Int>()

        for (regexStr in regexStrings) {
            val regex = Regex(regexStr)
            val matches = regex.findAll(input)
            val count = matches.count()
            counts[regexStr] = count
        }

        return counts
    }
}