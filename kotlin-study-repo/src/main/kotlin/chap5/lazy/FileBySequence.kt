package chap5.lazy

import java.io.File

fun File.isInsideHiddenDirectory() {
    generateSequence(this) { it.parentFile }.any { it.isHidden }
}