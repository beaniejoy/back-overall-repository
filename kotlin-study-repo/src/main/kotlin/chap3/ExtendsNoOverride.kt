package chap3

open class View {
    open fun click() = println("View clicked")
}

class Button: View() {
    override fun click() = println("Button clicked")
}

fun View.showOff() {
    println("View ShowOff")
    this.click()
}

fun Button.showOff() {
    println("Button ShowOff")
    this.click()
}

fun main() {
    val view: View = Button()
    view.showOff()
}