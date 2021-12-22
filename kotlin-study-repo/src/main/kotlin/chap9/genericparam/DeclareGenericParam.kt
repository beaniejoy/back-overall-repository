package chap9.genericparam

interface Example<T> {
    fun hello(): T
}

class ExampleImpl<Z>(val z: Z) : Example<Z> {
    override fun hello(): Z {
        println("hello world")
        return z
    }
}

