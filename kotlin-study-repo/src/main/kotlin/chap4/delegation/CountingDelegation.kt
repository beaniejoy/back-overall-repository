package chap4.delegation

class CountingSet<T>(
    val innerSet: MutableCollection<T> = HashSet<T>()
) : MutableCollection<T> by innerSet {
    var objectsAdded = 0

    override fun add(element: T): Boolean {
        objectsAdded++
        return innerSet.add(element)
    }

    override fun addAll(c: Collection<T>): Boolean {
        objectsAdded += c.size
        return innerSet.addAll(c)
    }
}

fun main() {
    val cset = CountingSet<Int>()
    cset.addAll(listOf(1, 2, 2, 3))

    println("${cset.objectsAdded} objects were added, ${cset.size} remain")
}
