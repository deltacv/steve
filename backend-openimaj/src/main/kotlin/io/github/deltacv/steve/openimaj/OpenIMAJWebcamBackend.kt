package io.github.deltacv.steve.openimaj

import io.github.deltacv.steve.Webcam
import io.github.deltacv.steve.WebcamBackend
import org.openimaj.video.capture.VideoCapture

object OpenIMAJWebcamBackend : WebcamBackend {

    override val availableWebcams: List<Webcam>
        get() {
            val webcams = mutableListOf<Webcam>()

            for((i, device) in VideoCapture.getVideoDevices().withIndex()) {
                webcams.add(OpenIMAJWebcam(device, index = i))
            }

            return webcams
        }

}