package io.github.deltacv.steve

import io.github.deltacv.steve.openpnp.OpenPnpBackend
import nu.pattern.OpenCV
import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.highgui.HighGui
import org.opencv.imgproc.Imgproc
import kotlin.random.Random

fun main() {
    OpenCV.loadLocally()
    Webcam.backend = OpenPnpBackend

    for(webcam in Webcam.availableWebcams) {
        println(webcam.name)
        println(webcam.supportedResolutionsString)
    }

    val webcam = Webcam.availableWebcams.first()
    webcam.resolution = webcam.supportedResolutions.last()

    webcam.open()

    val frame = Mat()

    while(webcam.isOpen) {
        webcam.read(frame)
        Imgproc.cvtColor(frame, frame, Imgproc.COLOR_RGB2BGR)
        HighGui.imshow("Webcam", frame)
        HighGui.waitKey(1)
    }
}