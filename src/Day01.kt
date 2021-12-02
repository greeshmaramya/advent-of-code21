fun main() {
    fun part1(input: List<Int>): Int {
        var count = 0
        for(i in 1 until input.size){
            if(input[i]>input[i-1]) count++
        }
        return count
    }


    fun part2(input: List<Int>): Int {
        var count = 0
        var windowSum: Int
        var previousWindowSum = input.take(3).sum()
        for(i in 1..input.size-3){
            windowSum = input.subList(i,i+3).sum()
            if (windowSum>previousWindowSum) {
                count++
            }
            previousWindowSum = windowSum
        }
        return count
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01Test")
    println(part2(listAsInt(testInput)))

    val input = readInput("Day01")
    println(part1(listAsInt(input)))
    println(part2(listAsInt(input)))
}
