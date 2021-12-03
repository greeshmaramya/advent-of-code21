fun main() {
    fun part1(input: List<String>): Int {
        var gamma = ""
        var epsilon = ""
        val len = input[0].length
        for (i in 0 until len) {
            var zeros = 0
            var ones = 0
            for (j in input) {
                if (j[i] == '0') zeros++
                else ones++
            }
            if (zeros > ones) {
                gamma += '0'
                epsilon += '1'
            } else {
                gamma += '1'
                epsilon += '0'
            }
        }

        val gammaValue = Integer.parseInt(gamma, 2)
        val epsValue = Integer.parseInt(epsilon, 2)
        return gammaValue * epsValue
    }

    fun part2(input: List<String>): Int {
        var data = input
        var carbonData = input
        val len = input[0].length
        var zeros: Int
        var ones: Int
        while (data.size > 1) {
            for (i in 0 until len) {
                zeros = 0
                ones = 0
                for (j in data) {
                    if (j[i] == '0') zeros++
                    else ones++
                }
                data = if (zeros > ones) {
                    data.filter { it[i] == '0' }
                } else {
                    data.filter { it[i] == '1' }
                }
                if (data.size == 1) break
            }
        }
        while (carbonData.size > 1) {
            for (i in 0 until len) {
                zeros = 0
                ones = 0
                for (j in carbonData) {
                    if (j[i] == '0') zeros++
                    else ones++
                }
                carbonData = if (zeros > ones) {
                    carbonData.filter { it[i] == '1' }
                } else {
                    carbonData.filter { it[i] == '0' }
                }
                if (carbonData.size == 1) break
            }
        }

        //only one will be remaining in the list
        val oxygen = Integer.parseInt(data[0], 2)
        val carbon = Integer.parseInt(carbonData[0], 2)
        return oxygen * carbon
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03Test")
    println(part2(testInput))

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}