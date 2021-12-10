import java.util.*

fun main() {
    val input = readInput("Day10Test")

    fun syntaxLookUp(c: Char): Int {
        return when (c) {
            ')' -> 3
            ']' -> 57
            '}' -> 1197
            '>' -> 25137
            else -> 0
        }
    }

    fun autoCompleteLookUp(c: Char): Int {
        return when (c) {
            '(' -> 1
            '[' -> 2
            '{' -> 3
            '<' -> 4
            else -> 0
        }
    }

    fun day10Solution(): Pair<Int, Long> {
        var totalSyntaxScore = 0
        val incompleteLines = mutableListOf<Long>()
        input.forEach {
            val stack = Stack<Char>()
            var syntaxScore = 0
            var incompleteLineScore = 0L
            for (c in it) {
                when (c) {
                    '(', '{', '[', '<' -> stack.push(c)
                    ')' -> if (stack.isEmpty() || stack.peek() != '(') syntaxScore = syntaxLookUp(c) else stack.pop()
                    ']' -> if (stack.isEmpty() || stack.peek() != '[') syntaxScore = syntaxLookUp(c) else stack.pop()
                    '}' -> if (stack.isEmpty() || stack.peek() != '{') syntaxScore = syntaxLookUp(c) else stack.pop()
                    '>' -> if (stack.isEmpty() || stack.peek() != '<') syntaxScore = syntaxLookUp(c) else stack.pop()
                }
                if (syntaxScore != 0) break
            }
            totalSyntaxScore += syntaxScore

            if (stack.isNotEmpty() && syntaxScore == 0) {
                stack.reversed().forEach { c ->
                    incompleteLineScore *= 5
                    incompleteLineScore += autoCompleteLookUp(c)
                }
                incompleteLines.add(incompleteLineScore)
            }
        }
        val incompleteLinesScore = incompleteLines.sorted()[incompleteLines.size / 2]
        return Pair(totalSyntaxScore, incompleteLinesScore)
    }

    println(day10Solution().first)
    println(day10Solution().second)
}
