fun main() {
    fun day2Part1(commands: List<Pair<String, Int>>): Int {
        var horizontalDistance = 0
        var depth = 0
        commands.forEach {
            val (direction, value) = it
            when (direction) {
                "forward" -> horizontalDistance += value
                "up" -> depth -= value
                "down" -> depth += value
            }
        }
        return horizontalDistance * depth
    }

    fun day2Part2(commands: List<Pair<String, Int>>): Int {
        var horizontalDistance = 0
        var depth = 0
        var aim = 0
        commands.forEach {
            val (direction, value) = it
            when (direction) {
                "forward" -> {
                    depth += aim * value
                    horizontalDistance += value
                }
                "up" -> aim -= value
                "down" -> aim += value
            }
        }
        return horizontalDistance * depth
    }

    fun modifyInput(input: List<String>): List<Pair<String, Int>> {
        return input.map { command ->
            val (direction, value) = command.split(' ', limit = 2)
            Pair(direction, value.toInt())
        }
    }

    val input = readInput("Day02")
    val modifiedInput = modifyInput((input))
    println(day2Part1(modifiedInput))
    println(day2Part2(modifiedInput))
}