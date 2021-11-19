package chap2.kt

// 모든 분기 케이스마다 return 값이 있어야 한다.
fun ifTest(num: Int): String {
    return if (num > 100) {
        "over 100"
    } else
        ""
}