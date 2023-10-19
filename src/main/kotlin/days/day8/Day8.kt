package days.day8

import days.Day

class Day8 : Day() {
    override fun partOne(): Any {
        val instructions = readInput()

        val executedInstruction: ArrayList<Int> = ArrayList()

        var accumulator = 0
        var pointer = 0

        while (!executedInstruction.contains(pointer)) {
            executedInstruction.add(pointer)
            val instruction = instructions[pointer]

            when (instruction.substringBefore(" ")) {
                "nop" -> pointer++
                "acc" -> {
                    accumulator += instruction.substringAfter(" ").toInt()
                    pointer++
                }

                "jmp" -> pointer += instruction.substringAfter(" ").toInt()
            }
        }
        return accumulator
    }

    fun calculate(instructions: List<String>): Int {

        val executedInstruction: ArrayList<Int> = ArrayList()

        var accumulator = 0
        var pointer = 0

        while (!executedInstruction.contains(pointer)) {
            executedInstruction.add(pointer)
            val instruction = instructions[pointer]

            when (instruction.substringBefore(" ")) {
                "nop" -> pointer++
                "acc" -> {
                    accumulator += instruction.substringAfter(" ").toInt()
                    pointer++
                }

                "jmp" -> pointer += instruction.substringAfter(" ").toInt()
            }
            if (pointer >= instructions.size) {
                return accumulator
            }
        }
        return -1

    }

    override fun partTwo(): Any {
        val instructions  = readInput()
        for (i in instructions.indices) {
            val instructionsCopy = ArrayList(instructions)
            if (instructionsCopy[i].contains("nop")) {
                instructionsCopy[i] = instructionsCopy[i].replace("nop", "jmp")
            }else if (instructionsCopy[i].contains("jmp")) {
                instructionsCopy[i] = instructionsCopy[i].replace("jmp", "nop")
            }

            if (instructions[i] != "acc") {
                val res = calculate(instructionsCopy)
                if (res != -1) {
                    return res
                }
            }
        }
        return -1
    }

}