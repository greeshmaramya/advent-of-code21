fun main() {
    val input = readInput("Day09")
    val arr = Array(input.size) { BooleanArray(input[0].length) }
    val basinArr = mutableListOf<Int>()
    val rows = input.size
    val columns = input[0].length

    fun neighbours(i: Int, j: Int): Int {
        if (input[i][j] == '9' || arr[i][j]) {
            return 0
        }
        var result = 1
        if (j > 0) {
            if (input[i][j] < input[i][j - 1]) {
                result += neighbours(i, j - 1)
            }
        }
        if (j < columns - 1) {
            if (input[i][j] < input[i][j + 1]) {
                result += neighbours(i, j + 1)
            }
        }
        if (i > 0) {
            if (input[i][j] < input[i - 1][j]) {
                result += neighbours(i - 1, j)
            }
        }
        if (i < rows - 1) {
            if (input[i][j] < input[i + 1][j]) {
                result += neighbours(i + 1, j)
            }
        }
        //to indicate its already counted
        arr[i][j] = true
        return result
    }

    fun day09Sol() {
        var risk = 0
        for (i in input.indices) {
            for (j in input[i].indices) {
                var low = true
                if (j > 0) {
                    low = low && (input[i][j] < input[i][j - 1])
                }
                if (j < input[i].length - 1) {
                    low = low && (input[i][j] < input[i][j + 1])
                }
                if (i > 0) {
                    low = low && (input[i][j] < input[i - 1][j])
                }
                if (i < input.size - 1) {
                    low = low && (input[i][j] < input[i + 1][j])
                }
                if (low) {
                    risk += input[i][j].toString().toInt() + 1
                    basinArr.add(neighbours(i, j))
                }
            }
        }

        //answer for part1
        println(risk)

        val part2Ans = basinArr.sorted().takeLast(3)

        println(part2Ans[0] * part2Ans[1] * part2Ans[2])
    }

    day09Sol()

}
