fun main() {
    fun part1(input: List<Int>): Int {
        var count = 0
        for (i in 1 until input.size) {
            if (input[i] > input[i - 1]) count++
        }
        return count
    }

    fun part2(input: List<Int>): Int {
        val slidingSum = input.subList(0, input.size - 2)
            .mapIndexed { index, i -> i + input[index + 1] + input[index + 2] }
        return part1(slidingSum)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01Test")
    println(part2(listAsInt(testInput)))

    val input = readInput("Day01")
    println(part1(listAsInt(input)))
    println(part2(listAsInt(input)))
}
