fun main(args: Array<String>) {
    // map from indexes of found gears to list of part numbers adjacent to each gear, used in part 2
    val gears = mutableMapOf<Pair<Int, Int>, MutableList<Int>>()

    fun isSymbol(input: List<String>, i: Int, j: Int, attachedGears: MutableList<MutableList<Int>>): Boolean {
        if (input[i][j].isDigit() || input[i][j] == '.') return false
        if (input[i][j] == '*') {
            attachedGears.add(gears.getOrPut(Pair(i, j)) { mutableListOf() })
        }
        return true
    }

    val input = readInput(3, true)

    // solution to part 1
    var partNumberSum = 0

    var i = 0
    while (i < input.size) {
        var j = 0
        while (j < input[i].length) {
            if (input[i][j].isDigit()) {
                var isPart = false
                val attachedGears = mutableListOf<MutableList<Int>>()

                // check up-left
                if (i > 0 && j > 0 && isSymbol(input,i - 1, j - 1, attachedGears)) isPart = true
                // check left
                if (j > 0 && isSymbol(input, i, j - 1, attachedGears)) isPart = true
                // check down-left
                if (i < input.size - 1 && j > 0 && isSymbol(input, i + 1, j - 1, attachedGears)) isPart = true

                val sb = StringBuilder()
                while (j < input[i].length && input[i][j].isDigit()) {
                    sb.append(input[i][j])

                    // check up
                    if (i > 0 && isSymbol(input, i - 1, j, attachedGears)) isPart = true

                    // check down
                    if (i < input.size - 1 && isSymbol(input, i + 1, j, attachedGears)) isPart = true

                    j++
                }

                // check up-right
                if (i > 0 && j < input[i].length - 1 && isSymbol(input, i - 1, j, attachedGears)) isPart = true
                // check right
                if (j < input[i].length - 1 && isSymbol(input, i, j, attachedGears)) isPart = true
                // check down-right
                if (i < input.size - 1 && j < input[i].length - 1 && isSymbol(input, i + 1, j, attachedGears)) isPart = true

                if (isPart) {
                    val partNumber = Integer.parseInt(sb.toString())
                    partNumberSum += partNumber
                    for (gear in attachedGears) {
                        gear.add(partNumber)
                    }
                }
            }
            j++
        }
        i++
    }

    var gaerRatioSum = 0
    for (partsAttachedToGear in gears.values) {
        if (partsAttachedToGear.size == 2) {
            gaerRatioSum += partsAttachedToGear[0] * partsAttachedToGear[1]
        }
    }

    println(partNumberSum)
    println(gaerRatioSum)
}
