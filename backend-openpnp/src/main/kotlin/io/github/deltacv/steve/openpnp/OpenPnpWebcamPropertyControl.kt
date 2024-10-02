package io.github.deltacv.steve.openpnp

import io.github.deltacv.steve.WebcamProperty
import io.github.deltacv.steve.WebcamPropertyBounds
import io.github.deltacv.steve.WebcamPropertyControl
import org.openpnp.capture.CaptureException
import org.openpnp.capture.CaptureProperty
import org.openpnp.capture.CaptureStream

class OpenPnpWebcamPropertyControl(
    val stream: CaptureStream
): WebcamPropertyControl {

    private fun mapProperty(property: WebcamProperty): CaptureProperty {
        return when(property) {
            WebcamProperty.BACKLIGHT_COMPENSATION -> CaptureProperty.BackLightCompensation
            WebcamProperty.BRIGHTNESS -> CaptureProperty.Brightness
            WebcamProperty.CONTRAST -> CaptureProperty.Contrast
            WebcamProperty.EXPOSURE -> CaptureProperty.Exposure
            WebcamProperty.FOCUS -> CaptureProperty.Focus
            WebcamProperty.GAIN -> CaptureProperty.Gain
            WebcamProperty.GAMMA -> CaptureProperty.Gamma
            WebcamProperty.HUE -> CaptureProperty.Hue
            WebcamProperty.POWERLINE_FREQUENCY -> CaptureProperty.PowerLineFrequency
            WebcamProperty.SATURATION -> CaptureProperty.Saturation
            WebcamProperty.SHARPNESS -> CaptureProperty.Sharpness
            WebcamProperty.WHITEBALANCE -> CaptureProperty.WhiteBalance
            WebcamProperty.ZOOM -> CaptureProperty.Zoom
        }
    }

    override fun getProperty(property: WebcamProperty) =
        stream.getProperty(mapProperty(property))

    override fun setProperty(property: WebcamProperty, value: Int) =
        stream.setProperty(mapProperty(property), value)

    override fun setPropertyAuto(property: WebcamProperty, value: Boolean) =
        stream.setAutoProperty(mapProperty(property), value)

    override fun getPropertyAuto(property: WebcamProperty) =
        stream.getAutoProperty(mapProperty(property))

    override fun getPropertyBounds(property: WebcamProperty): WebcamPropertyBounds {
        val bounds = stream.getPropertyLimits(mapProperty(property))
        return WebcamPropertyBounds(bounds.min, bounds.max, bounds.default)
    }

    override fun isPropertySupported(property: WebcamProperty): Boolean {
        try {
            stream.setProperty(mapProperty(property), stream.getProperty(mapProperty(property)))
            return true
        } catch(_: CaptureException) {
            return false
        }
    }
}