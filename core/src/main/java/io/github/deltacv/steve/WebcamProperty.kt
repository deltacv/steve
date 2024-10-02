package io.github.deltacv.steve

data class WebcamPropertyBounds(
    val min: Int,
    val max: Int,
    val default: Int
)

enum class WebcamProperty {
    BACKLIGHT_COMPENSATION,
    BRIGHTNESS,
    CONTRAST,
    EXPOSURE,
    FOCUS,
    GAIN,
    GAMMA,
    HUE,
    POWERLINE_FREQUENCY,
    SATURATION,
    SHARPNESS,
    WHITEBALANCE,
    ZOOM,
}