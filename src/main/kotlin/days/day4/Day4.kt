package days.day4

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


        val parts = passportEntries.map { it.split(' ').map{it.split(':')} }
        val passports = parts.map { entry -> entry.map { it[0] to it[1] }.toMap() }

        var result = 0

        for (passport in passports) {
            if (isPassportValidValue(passport)) {
                result++
            }
        }

        return result

    }

    private fun isPassportValidValue(passport: Map<String, String>):Boolean {
        if (!isPassportValid(countSubstrings(passport.toString()))) return false
        if (passport["byr"]!!.toInt() < 1920 || passport["byr"]?.toInt()!! > 2002) return false
        if (passport["iyr"]!!.toInt() < 2010 || passport["iyr"]?.toInt()!! > 2020) return false
        if (passport["eyr"]!!.toInt() < 2020 || passport["eyr"]?.toInt()!! > 2030) return false

        val hgt = passport["hgt"]!!
        if(hgt.contains("cm")) {
            val height = hgt.substring(0, hgt.length - 2).toInt()
            if (height < 150 || height > 193) return false
        } else if (hgt.contains("in")) {
            val height = hgt.substring(0, hgt.length - 2).toInt()
            if (height < 59 || height > 76) return false
        } else {
            return false
        }

        if (!Regex("#[0-9a-f]{6}").matches(passport["hcl"]!!)) return false
        if (!Regex("amb|blu|brn|gry|grn|hzl|oth").matches(passport["ecl"]!!)) return false
        if (!Regex("[0-9]{9}").matches(passport["pid"]!!)) return false
        return true
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