import kotlin.math.pow

fun main(args: Array<String>) {
    class CardEntry(val winningNumbers: Set<Int>, val myNumbers: Set<Int>) {
        var count = 1
        val matches = myNumbers.intersect(winningNumbers).size
    }

    fun numbersFromString(winningStr: String) = winningStr.trim().split("\\s+".toRegex()).map { Integer.parseInt(it) }

    fun makeCardEntries(input: List<String>): List<CardEntry> {
        return input.map {
            val contents = it.split(":", limit = 2)[1]
            val (winningStr, myStr) = contents.split("|", limit = 2)
            val winningNumbers = numbersFromString(winningStr).toSet()
            val myNumbers = numbersFromString(myStr).toSet()
            CardEntry(winningNumbers, myNumbers)
        }
    }

    fun score(cardEntry: CardEntry): Int {
        return if (cardEntry.matches < 2) {
            cardEntry.matches
        } else {
            1 shl (cardEntry.matches - 1)
        }
    }

    fun part1(cardEntries: List<CardEntry>) {
        var totalScore = 0
        for (cardEntry in cardEntries) {
            totalScore += score(cardEntry)
        }
        println(totalScore)
    }

    fun part2(cardEntries: List<CardEntry>) {
        var cardsProcessed = 0
        for (i in cardEntries.indices) {
            val cardEntry = cardEntries[i]
            while (cardEntry.count > 0) {
                var j = i + 1
                while (j <= i + cardEntry.matches) {
                    cardEntries[j].count++
                    j++
                }

                cardsProcessed++
                cardEntry.count--
            }
        }

        println(cardsProcessed)
    }

    val input = readInput(4, true)
    val cardEntries = makeCardEntries(input)

    part1(cardEntries)
    part2(cardEntries)
}
