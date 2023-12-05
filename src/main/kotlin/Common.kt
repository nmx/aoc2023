fun readInput(day: Int, prod: Boolean): List<String> {
    return object {}.javaClass.getResource(String.format("Day%02d_${if (prod) "prod" else "test"}.txt", day))
        .readText().trimEnd().split("\n")
}
