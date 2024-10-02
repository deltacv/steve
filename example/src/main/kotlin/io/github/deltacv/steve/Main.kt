package io.github.deltacv.steve

import io.github.deltacv.steve.openpnp.OpenPnpBackend
import nu.pattern.OpenCV
import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.highgui.HighGui
import kotlin.random.Random

fun main() {
    OpenCV.loadLocally()
    Webcam.backend = OpenPnpBackend

    for(webcam in Webcam.availableWebcams) {
        println(webcam.name)
    }

    val webcam = Webcam.availableWebcams.first()

    webcam.open()

    val frame = Mat()

    while(true) {
        webcam.read(frame)
        HighGui.imshow("Webcam", frame)
        HighGui.waitKey(1)
    }
}