package tabplay

import java.nio.file.Path


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

data class Test(
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
)

data class Submission(
    val id: Int,
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

fun lineToTest(line: String): Test? {
    val cols = line.split(",")
    if (cols[0] == "id") return null
    return Test(
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
    )
}

val workDir: Path = Path.of("/data/work/tabplay")
val dataDir: Path = workDir.resolve("data")

fun main() {
    val trains = readData("train.csv", ::lineToTrain)
    val m: Model = ModelMean(trains)
    val subs = readData("train.csv", ::lineToTest).map(m::predict)
    
    subs.take(10).forEach(::println)
}

fun <T> readData(fileName: String, f: (String) -> T?): List<T> {
    return dataDir.resolve(fileName).toFile()
        .useLines { it.toList() }
        .mapNotNull { f(it) }
}

abstract class Model {

    abstract fun predict(test: Test): Submission

}

class ModelMean(train: List<Train>) : Model() {

    private val sum = train.map { it.target }.sum()
    private val mean = sum / train.size

    override fun predict(test: Test): Submission {
        return Submission(test.id, mean)
    }

}
