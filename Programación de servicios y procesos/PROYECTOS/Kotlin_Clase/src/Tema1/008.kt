fun main() {

    repeat(3) {

        Thread.sleep(2000)

        val top = ProcessBuilder("bash",
            "-c",
            "ps -eo pid,comm,%cpu --sort=-%cpu | head -n 4"
        ).start()

        println("=== Muestra ${it + 1} ===")

        top.inputStream.bufferedReader()
            .lines()
            .forEach(::println)
    }
}