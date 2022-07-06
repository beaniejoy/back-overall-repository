package testpack

fun testPrint(value: String?): Int? {
    return value?.toInt()
}

data class Test(
    val value1: String,
    val value2: String
)

fun main() {
    FunctionDemo.readAndPrint { value: String? -> testPrint(value) }

    val tmpList = listOf<String?>("beanie", null, "joy")
    for (tmp in tmpList) {
        println(tmp)
    }
}