fun main() {
    val input = readInput("Day14Test")
    val polymer = input.first()
    val rules = input.subList(2, input.size).map {
        Pair(it.split(" -> ").first(), it.split(" -> ")[1])
    }


    val result = HashMap<Char,Long>() // counting the characters

    polymer.forEach {
        result[it] = result.getOrDefault(it,0)+1
    }

    val hp = HashMap<String, Long>()
    val inputPairs = polymer.zipWithNext().map {
        it.first + "" + it.second
    }
    inputPairs.forEach {
        hp[it] = hp.getOrDefault(it, 0) + 1
    }

    // storing the pairs in hashmap, eg for rule AB-> C hashmap will now have AC, CB
    fun day14(steps:Int): Long {
        for (i in 0 until steps) {
            val h = HashMap<String,Long>()
            h.putAll(hp)
            for (r in rules.indices) {
                val rule = rules[r].first
                val value = rules[r].second
                if (h.containsKey(rule) && h[rule]!=0L) {
                    hp[rule[0] + value] = hp.getOrDefault(rule[0] + value, 0) + h[rule]!!
                    hp[value + rule[1]] = hp.getOrDefault(value + rule[1], 0) + h[rule]!!
                    hp[rule] = hp.getOrDefault(rule, 0) - h[rule]!!
                    result[value.first()] = result.getOrDefault(value.first(),0) + h[rule]!!.toLong()
                }
            }
        }

        return result.values.maxOrNull()!!- result.values.minOrNull()!!
    }

    println(day14(10)) //part1

    println(day14(40)) //part2

}
