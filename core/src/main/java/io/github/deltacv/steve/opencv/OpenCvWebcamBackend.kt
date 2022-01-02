package io.github.deltacv.steve.opencv

import io.github.deltacv.steve.Webcam
import io.github.deltacv.steve.WebcamBackend
import org.opencv.videoio.VideoCapture

object OpenCvWebcamBackend : WebcamBackend {

    var emptyCamerasBeforeGivingUp = 1

    override val availableWebcams: List<Webcam> get() {
        val webcams = mutableListOf<Webcam>()

        var capture: VideoCapture? = null
        var currentIndex = 0

        var emptyCameras = 0

        do {
            if(capture != null && capture.isOpened) {
                capture.release()
            }

            capture = VideoCapture(currentIndex)
            capture.open(currentIndex)

            if(capture.isOpened) {
                webcams.add(OpenCvWebcam(currentIndex))
                emptyCameras = 0
            } else {
                emptyCameras++
            }

            currentIndex++
        } while((capture != null && capture.isOpened) || emptyCameras <= emptyCamerasBeforeGivingUp)

        return webcams
    }


}