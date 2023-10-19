package days.day7

import days.Day

class Day7:Day() {
    override fun partOne(): Any {
        return getValidBagCunt("shiny gold", readInput()).count()
    }

    fun getValidBagCunt(bag: String, input: List<String>): Set<String> {
        val validBags = input.filter{it.substringAfter(" ").contains(bag)}
        if (validBags.isEmpty()) {
            return setOf()
        }
        val result = validBags.map{it.substringBefore(" bags")}.toSet().toMutableSet()
        for (validBag in validBags) {
            result += getValidBagCunt(validBag.substringBefore(" bags"), input)
        }
        return result
    }

    fun countBagsInGOldBag(bag: String , input: List<String>): Int{
        val validBags = input.filter{it.substringBefore(" contain").contains(bag)}
        val containedBags = validBags.map { it.substringAfter("contain ").split(", ").map{it.replace(".","")} }.flatten()

        var result = 1
        for (containedBag in containedBags) {
            val numberOfBags = containedBag.substringBefore(" ").toIntOrNull() ?: return 1
            result += numberOfBags * countBagsInGOldBag(containedBag.substringAfter(" "), input)
        }
        return result
    }

    override fun partTwo(): Any {
        return countBagsInGOldBag("shiny gold", readInput())-1
    }

}