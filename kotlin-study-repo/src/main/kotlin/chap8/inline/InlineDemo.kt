package chap8.inline

import sun.misc.Lock

fun foo(l: Lock) {
    println("Before sync")
    synchronized(l) {
        println("Action")
    }
    println("After sync")
}

class LockOwner(val lock: Lock) {
    fun runUnderLock(body: () -> Unit) {
        synchronized(lock, body)
    }
}

fun main(args: Array<String>) {
    val l = Lock()
    foo(l)
}