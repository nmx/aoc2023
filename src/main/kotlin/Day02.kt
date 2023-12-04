fun main(args: Array<String>) {
    class Game(val idLabel: String, val sets: List<String>) {
        fun id(): Int {
            return Integer.parseInt(idLabel.substring("Game ".length))
        }
    }

    fun makeGame(line: String): Game {
        val (gameLabel, setsStr) = line.split(": ", limit = 2)
        val sets = setsStr.split("; ")
        return Game(gameLabel, sets)
    }

    fun isGamePossible(game: Game, requiredCounts: Map<String, Int>): Boolean {
        for (set in game.sets) {
            val ballCounts = set.split(", ")
            for (ballCount in ballCounts) {
                val (count, color) = ballCount.split(" ", limit = 2)
                if (Integer.parseInt(count) > requiredCounts.getValue(color)) {
                    return false
                }
            }
        }
        return true
    }

    fun gamePower(game: Game): Int {
        val maxes = mutableMapOf<String, Int>()
        for (set in game.sets) {
            val ballCounts = set.split(", ")
            for (ballCount in ballCounts) {
                val (countStr, color) = ballCount.split(" ", limit = 2)
                val count = Integer.parseInt(countStr)
                if (count > maxes.getOrPut(color) {0}) {
                    maxes.put(color, count)
                }
            }
        }
        var product = 1
        for (max in maxes.values) {
            product *= max
        }
        return product
    }

    fun part1(games: Iterable<Game>) {
        val requiredCounts = mapOf(
            "red" to 12,
            "green" to 13,
            "blue" to 14
        )

        val possibleGameIdSum = games.filter { isGamePossible(it, requiredCounts) }.sumOf { it.id() }
        println(possibleGameIdSum)
    }

    fun part2(games: Iterable<Game>) {
        val powerSum = games.map { gamePower(it) }.sum()
        print(powerSum)
    }

    val input = object {}.javaClass.getResource("Day02_prod.txt").readText()
    val games = input.split("\n").map {
        if (it.isBlank()) null else makeGame(it)
    }.filterNotNull()

    part1(games)
    part2(games)
}
