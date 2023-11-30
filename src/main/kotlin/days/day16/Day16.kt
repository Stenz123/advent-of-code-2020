package days.day16

import days.Day
import java.util.LinkedList

class Day17 : Day() {
    override fun partOne(): Any {
        val input = readInput()

        val rules = parseRules()
        val tickets = parseTickets()

        val ranges = rules.map { it.range }.flatten()

        val invalidValues = tickets.map { ticket ->
            ticket.filter { value ->
                ranges.none { range ->
                    range.contains(value)
                }
            }
        }.flatten()

        return invalidValues.sum()
    }

    private fun parseTickets(): ArrayList<List<Int>> {
        //7,3,47
        val unparsed = readInput().takeLastWhile { it != "nearby tickets:" }
        println(unparsed)
        val result = arrayListOf<ArrayList<Int>>()
        unparsed.forEach {
            val split = it.split(",")
            var ticket = arrayListOf<Int>()
            split.forEach { s ->
                ticket.add(s.toInt())
            }
            result.add(ticket)
        }
        return ArrayList(result)
    }

    fun parseRules(): ArrayList<Rule> {
        val rulesUnparsed = readInput().takeWhile { it.isNotBlank() }
        val rules = arrayListOf<Rule>()
        rulesUnparsed.forEach {
            val nameValues = ArrayList(it.split(":"))
            val restult = Rule(nameValues[0], arrayListOf())

            val split = nameValues[1].split(" or ").map { it.trim() }

            for (s in split) {
                val values = s.split("-")
                restult.range.add(IntRange(values[0].toInt(), values[1].toInt()))
            }
            rules.add(restult)
        }
        return rules
    }

    override fun partTwo(): Any {
        val input = readInput()

        val rules = parseRules()
        val tickets = parseTickets()

        val ranges = rules.map { it.range }.flatten()

        val validTickets = tickets.filter { ticket ->
            ticket.all { value ->
                ranges.any { range ->
                    range.contains(value)
                }
            }
        }

        val possibleRulePositions = HashMap<Rule, ArrayList<Int>>()

        for (rule in rules) {
            for (i in 0..<validTickets[0].size)
                if (validTickets.all { rule.range.any { range -> range.contains(it[i]) } }) {
                    if (possibleRulePositions[rule] == null) {
                        possibleRulePositions[rule] = ArrayList()
                    }
                    possibleRulePositions[rule]?.add(i)
                }
        }

        val myTicketRaw = readInput().takeLastWhile { it != "your ticket:" }.first().split(",").map { it.toInt() }

        var result = 1

        val combinations = findCombinations(possibleRulePositions)
        findCombinations(possibleRulePositions).forEach { (rule, position) ->
            println("${rule.identifier} at position $position")
            if (rule.identifier.startsWith("departure")){
                println("Found departure rule: $rule at position $position")
                result *=myTicketRaw[position]
            }
        }


        return result
    }

    fun findCombinations(map: Map<Rule, List<Int>>): Map<Rule, Int> {
        val keys = map.keys.toList()
        val result = mutableMapOf<Rule, Int>()

        fun backtrack(index: Int, currentAssignment: MutableMap<Rule, Int>) {
            if (index == keys.size) {
                result.putAll(currentAssignment)
                return
            }

            val currentKey = keys[index]
            val possiblePositions = map[currentKey] ?: emptyList()

            for (position in possiblePositions) {
                if (!currentAssignment.values.contains(position)) {
                    currentAssignment[currentKey] = position
                    backtrack(index + 1, currentAssignment)
                    currentAssignment.remove(currentKey)
                }
            }
        }

        backtrack(0, mutableMapOf())
        return result
    }

}

class Rule(val identifier: String, val range: ArrayList<IntRange>) {
    override fun toString(): String {
        return "Rule(identifier='$identifier', range=$range)"
    }
}
