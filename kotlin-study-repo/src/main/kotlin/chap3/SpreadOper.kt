package chap3

/*
* vararg values: String -> values: Array<String> (배열 대상)
* java에서는 String...
*/
fun printVararg (vararg values: String) {
    for (value in values) {
        println(value)
    }
}