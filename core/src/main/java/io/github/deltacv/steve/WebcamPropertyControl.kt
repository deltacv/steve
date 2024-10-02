package io.github.deltacv.steve

interface WebcamPropertyControl {
    fun getProperty(property: WebcamProperty): Int
    fun setProperty(property: WebcamProperty, value: Int)
}