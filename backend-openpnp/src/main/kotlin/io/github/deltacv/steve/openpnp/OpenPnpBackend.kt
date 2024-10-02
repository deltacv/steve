package io.github.deltacv.steve.openpnp

import io.github.deltacv.steve.Webcam
import io.github.deltacv.steve.WebcamBackend
import org.openpnp.capture.OpenPnpCapture
import org.slf4j.LoggerFactory

object OpenPnpBackend : WebcamBackend {
    val capture = OpenPnpCapture()

    val logger = LoggerFactory.getLogger(OpenPnpBackend::class.java)

    override val availableWebcams: List<Webcam> get() {
        val webcams = mutableListOf<Webcam>()

        for((i, device) in capture.devices.withIndex()) {
            try {
                webcams.add(OpenPnpWebcam(device, index = i))
            } catch(e: Exception) {
                logger.warn("Detected webcam ${device.name} but failed to construct it", e)
            }
        }

        return webcams
    }
}