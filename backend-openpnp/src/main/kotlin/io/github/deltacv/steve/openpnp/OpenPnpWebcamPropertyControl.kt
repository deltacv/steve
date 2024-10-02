package io.github.deltacv.steve.openpnp

import io.github.deltacv.steve.WebcamProperty
import io.github.deltacv.steve.WebcamPropertyControl
import org.openpnp.capture.CaptureProperty
import org.openpnp.capture.CaptureStream

class OpenPnpWebcamPropertyControl(
    val stream: CaptureStream
): WebcamPropertyControl {

    private fun mapWebcamPropertyToOpenPnpProperty(property: WebcamProperty): CaptureProperty {
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
        stream.getProperty(mapWebcamPropertyToOpenPnpProperty(property))

    override fun setProperty(property: WebcamProperty, value: Int) =
        stream.setProperty(mapWebcamPropertyToOpenPnpProperty(property), value)


}