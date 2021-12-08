fun main() {

    // test if implementation meets criteria from the description, like:
    val input = readInput("Day08")

    val size = input.size

    val digitInputs = input.map {
        it.split("|")[1]
    }

    val signalInputs = input.map {
        it.split("|")[0]
    }

    fun String.containsAllChars(s: String): Boolean {
        var t = true
        s.split("").filter { it.isNotBlank() }.forEach {
            t = t && contains(it)
        }
        return t
    }

    fun String.sortString() = toCharArray().sorted().joinToString("")

    val numbers = HashMap<Int, String>()

    fun part1(): Int {
        var count = 0
        for (i in 0 until size) {
            val c = digitInputs[i].split(" ")
            for (j in c.indices) {
                if (c[j].length == 2 || c[j].length == 4 || c[j].length == 3 || c[j].length == 7) {
                    count++
                }
            }
        }
        return count
    }

/*
     __
    |__|
    |__|

 */

    fun decodeSignals(list: List<String>) {
        numbers[1] = list.find { it.length == 2 }!!
        numbers[4] = list.find { it.length == 4 }!!
        numbers[7] = list.find { it.length == 3 }!!
        numbers[8] = list.find { it.length == 7 }!!
        // 6 wont have both of 1's segments
        numbers[6] = list.find { it.length == 6 && !it.containsAllChars(numbers[1]!!) }!!
        // 9 and 6 will have 4's segments but 0 won't
        numbers[9] = list.find { it.length == 6 && it != numbers[6]!! && it.containsAllChars(numbers[4]!!) }!!
        // only 0,6,9 have 6 total segments
        numbers[0] = list.find { it.length == 6 && it != numbers[6]!! && it != numbers[9]!! }!!
        // 3,2,5 have 5 segments
        // only 3 will have 1's segments
        numbers[3] = list.find { it.length == 5 && it.containsAllChars(numbers[1]!!) }!!
        // 9 will contain all of 5's segments
        numbers[5] = list.find { it.length == 5 && it != numbers[3]!! && numbers[9]!!.containsAllChars(it) }!!
        numbers[2] = list.find { it.length == 5 && it != numbers[3]!! && it != numbers[5]!! }!!
    }

    fun part2() {
        var c = 0
        for (i in 0 until size) {
            var decodedNumber = ""
            val digits = digitInputs[i].split(" ").map {
                it.sortString()
            }
            val signals = signalInputs[i].split(" ").map {
                it.sortString()
            }

            decodeSignals(signals)
            digits.forEach {  digit ->
                when (digit.length) {
                    2 -> decodedNumber += '1'
                    4 -> decodedNumber += '4'
                    3 -> decodedNumber += '7'
                    7 -> decodedNumber += '8'
                    6 -> {
                        when (digit) {
                            numbers[6] -> decodedNumber += '6'
                            numbers[9] -> decodedNumber += '9'
                            numbers[0] -> decodedNumber += '0'
                        }
                    }
                    5 -> {
                        when (digit) {
                            numbers[5] -> decodedNumber += '5'
                            numbers[2] -> decodedNumber += '2'
                            numbers[3] -> decodedNumber += '3'
                        }
                    }
                }

            }
            c += decodedNumber.toInt()
            print(decodedNumber)
            println(numbers)

        }
        println(c)
    }

    println(part1())
    part2()
}