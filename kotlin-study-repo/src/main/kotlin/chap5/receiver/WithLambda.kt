package chap5.receiver

fun alphabet(): String = with(StringBuilder()) {
    for (letter in 'A'..'Z') {
        append(letter)
    }

    append("\n Now I know the alphabet!")
    toString()
}

class OuterClass(val outer: String) {

    fun alphabet(): String = with(StringBuilder()) {
        for (letter in 'A'..'Z') {
            append(letter)
        }

        append("\n Now I know the alphabet!")
        println("alphabet: ${toString()}")  // 수신객체의 method를 활용하고 싶을 땐 this도 안붙여도 된다.
        // OuterClass의 toString()을 호출하고 싶으면
        // @OuterClass를 통해 바깥지점 참조를 알려줘야 한다.
        this@OuterClass.toString()
    }

    override fun toString(): String {
        return "OuterClass(outer=$outer)"
    }
}

class Example {
    var name: String = ""

    override fun toString(): String {
        return "Example(name='$name')"
    }
}

fun main() {
    val outer = OuterClass("beanie")

    println(outer.alphabet())

    val example = Example()
    // setter 가능
    example.name = "beanie"
    println(example)
}