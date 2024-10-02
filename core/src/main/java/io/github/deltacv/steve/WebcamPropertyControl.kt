package io.github.deltacv.steve

interface WebcamPropertyControl {
    fun getProperty(property: WebcamProperty): Int
    fun setProperty(property: WebcamProperty, value: Int)

    fun setPropertyAuto(property: WebcamProperty, value: Boolean)
    fun getPropertyAuto(property: WebcamProperty): Boolean

    fun getPropertyBounds(property: WebcamProperty): WebcamPropertyBounds

    fun isPropertySupported(property: WebcamProperty): Boolean
}