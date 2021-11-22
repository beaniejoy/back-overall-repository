package chap4.anonymous

import java.awt.Window
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

fun countClicks(window: Window) {
    var clickCount = 0

    window.addMouseListener(object : MouseAdapter() {
        override fun mouseClicked(e: MouseEvent) {
            clickCount++
        }
    })
}