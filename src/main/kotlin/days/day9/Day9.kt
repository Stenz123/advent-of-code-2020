package days.day9

import days.Day

class Day9 : Day() {
    override fun partOne(): Any {
        val preambelSize = 25
        val input = readInput().map (String::toLong)

        for (i in preambelSize..<input.size) {
            val subList = input.subList(i-preambelSize,i)
            if (!isValidNumber(input[i], subList)) {
                return input[i]
            }
        }
return -1
    }

    private fun isValidNumber(number: Long, list: List<Long>): Boolean {
        for (a in list) {
            for (b in list) {
                if (a != b) {
                    if (a + b == number) {
                        return true
                    }
                }
            }
        }
        return false
    }

    override fun partTwo(): Any {
        val invalidNUmber:Long = partOne() as Long
        val input = readInput().map (String::toLong)

        for (i in 0..<input.size) {
            var sum = 0L
            var j = i
            while (sum < invalidNUmber) {
                sum += input[j]
                j++
            }
            if (sum == invalidNUmber) {
                val subList = input.subList(i,j)
                return subList.min()!! + subList.max()!!
            }
        }
        return -1
    }

}