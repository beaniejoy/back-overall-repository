package chap8.label

/*
* label 통해서 바깥의 람다 호출을 한 this와 람다 내부에서 호출한 this를 구분지을 수 있다.
*/
fun main() {
    println(StringBuilder().apply sb@{
        listOf(1, 2, 3).apply {
            this@sb.append(this.toString())
        }
    })
}