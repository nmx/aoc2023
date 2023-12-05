fun main(args: Array<String>) {
    val textToDigit = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9
    )

    fun getDigit(sourceStr: String, i: Int, stringBuilder: StringBuilder, allowText: Boolean): Boolean {
        if (sourceStr[i].isDigit()) {
            stringBuilder.append(sourceStr[i])
            return true
        }
        if (!allowText) {
            return false
        }
        for (e in textToDigit.entries) {
            if (i + e.key.length > sourceStr.length) continue
            if (sourceStr.substring(i, i + e.key.length) == e.key) {
                stringBuilder.append(e.value);
                return true;
            }
        }
        return false
    }

    fun process(input: List<String>, allowText: Boolean) {
        var sum = 0
        for (sourceStr in input) {
            val stringBuilder = StringBuilder()
            for (i in sourceStr.indices) {
                if (getDigit(sourceStr, i, stringBuilder, allowText)) break
            }
            for (i in sourceStr.indices.reversed()) {
                if (getDigit(sourceStr, i, stringBuilder, allowText)) break
            }
            sum += Integer.parseInt(stringBuilder.toString())
        }
        println(sum)
    }

    fun part1(input: List<String>) {
        process(input, false)
    }

    fun part2(input: List<String>) {
        process(input, true)
    }

    val input = readInput(1, true)
    part1(input)
    part2(input)
}
