package tabplay

import java.io.File


data class Train(
    val id: Int,
    val cont1: Double,
    val cont2: Double,
    val cont3: Double,
    val cont4: Double,
    val cont5: Double,
    val cont6: Double,
    val cont7: Double,
    val cont8: Double,
    val cont9: Double,
    val cont10: Double,
    val cont11: Double,
    val cont12: Double,
    val cont13: Double,
    val cont14: Double,
    val target: Double,
)

fun lineToTrain(line: String): Train? {
    val cols = line.split(",")
    if (cols[0] == "id") return null
    return Train(
        cols[0].toInt(),
        cols[1].toDouble(),
        cols[2].toDouble(),
        cols[3].toDouble(),
        cols[4].toDouble(),
        cols[5].toDouble(),
        cols[6].toDouble(),
        cols[7].toDouble(),
        cols[8].toDouble(),
        cols[9].toDouble(),
        cols[10].toDouble(),
        cols[11].toDouble(),
        cols[12].toDouble(),
        cols[13].toDouble(),
        cols[14].toDouble(),
        cols[15].toDouble(),
    )
}

fun main(args: Array<String>) {
    println("hello kotlin")
    val fileName = "/data/work/tabplay/data/train.csv"
    File(fileName)
        .useLines { it.toList() }
        .mapNotNull { lineToTrain(it) }
        .take(20)
        .forEach { println(it) }

}

