package days.day17

import days.Day

class Day17 : Day() {
    override fun partOne(): Any {
        val input = readInput()

        val cubes: MutableSet<Cube> = mutableSetOf()

        for (y in input.indices) {
            for (x in input[y].indices) {
                if (input[y][x] == '#') {
                    cubes.add(Cube(x, y, 0, true))
                }
            }
        }

        for (i in 1..6) {
            printIt(cubes)
            var temp: MutableSet<Cube> = mutableSetOf()
            cubes.forEach { temp.addAll(it.getAllNeighbours()) }
            cubes.addAll(temp)

            temp = mutableSetOf()

            for (cube in cubes) {
                val cubeClone = Cube(cube.x, cube.y, cube.z, cube.isActive)
                val activeNeighbours = cubes.filter { cubeClone.isNeighbour(it) }.count { it.isActive }
                if (cubeClone.isActive) {
                    cubeClone.isActive = activeNeighbours == 2 || activeNeighbours == 3
                } else if (activeNeighbours == 3) {
                    cubeClone.isActive = true
                }
                if (cubeClone.isActive) {
                    temp.add(cubeClone)
                }

            }
            cubes.clear()
            cubes.addAll(temp)
        }
        return cubes.count { it.isActive }
    }


    override fun partTwo(): Any {
        val input = readInput()

        val cubes: MutableSet<HyperCube> = mutableSetOf()

        for (y in input.indices) {
            for (x in input[y].indices) {
                if (input[y][x] == '#') {
                    cubes.add(HyperCube(x, y, 0, 0,true))
                }
            }
        }

        for (i in 1..6) {
            //hyperPrintIt(cubes)
            var temp: MutableSet<HyperCube> = mutableSetOf()
            cubes.forEach { temp.addAll(it.getAllNeighbours()) }
            cubes.addAll(temp)

            temp = mutableSetOf()

            for (cube in cubes) {
                val cubeClone = HyperCube(cube.x, cube.y, cube.z, cube.w, cube.isActive)
                val activeNeighbours = cubes.filter { cubeClone.isNeighbour(it) }.count { it.isActive }
                if (cubeClone.isActive) {
                    cubeClone.isActive = activeNeighbours == 2 || activeNeighbours == 3
                } else if (activeNeighbours == 3) {
                    cubeClone.isActive = true
                }
                if (cubeClone.isActive) {
                    temp.add(cubeClone)
                }

            }
            cubes.clear()
            cubes.addAll(temp)
        }
        return cubes.count { it.isActive }
    }

    fun printIt(cubes: Set<Cube>) {
        println("--------------------")

        val minX = cubes.minOf { it.x }
        val maxX = cubes.maxOf { it.x }
        val minY = cubes.minOf { it.y }
        val maxY = cubes.maxOf { it.y }
        val minZ = cubes.minOf { it.z }
        val maxZ = cubes.maxOf { it.z }

        for (z in minZ..maxZ) {
            println("z=$z")
            for (y in minY..maxY) {
                for (x in minX..maxX) {
                    if (cubes.find { it.x == x && it.y == y && it.z == z }?.isActive == true) {
                        print("#")
                    } else {
                        print(".")
                    }
                }
                println()
            }
            println()
        }
    }

    fun hyperPrintIt(cubes: Set<HyperCube>) {
        println("--------------------")

        val minW = cubes.minOf { it.w }
        val maxW = cubes.maxOf { it.w }
        val minX = cubes.minOf { it.x }
        val maxX = cubes.maxOf { it.x }
        val minY = cubes.minOf { it.y }
        val maxY = cubes.maxOf { it.y }
        val minZ = cubes.minOf { it.z }
        val maxZ = cubes.maxOf { it.z }

        for (w in minW..maxW) {
            for (z in minZ..maxZ) {
                println("w: $w")
                println("z=$z")
                for (y in minY..maxY) {
                    for (x in minX..maxX) {
                        if (cubes.find { it.x == x && it.y == y && it.z == z && it.w == w}?.isActive == true) {
                            print("#")
                        } else {
                            print(".")
                        }
                    }
                    println()
                }
                println()
            }


        }
    }
}