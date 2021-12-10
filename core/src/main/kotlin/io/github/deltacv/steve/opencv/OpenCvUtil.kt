package io.github.deltacv.steve.opencv

import io.github.deltacv.steve.commonResolutions
import org.opencv.core.Size
import org.opencv.videoio.VideoCapture
import org.opencv.videoio.Videoio

object OpenCvUtil {

    // adapted from https://www.learnpythonwithrune.org/find-all-possible-webcam-resolutions-with-opencv-in-python/
    // with a predefined list of commonResolutions because pulling from wikipedia is dumb lol
    @JvmStatic fun getResolutionsOf(index: Int): List<Size> {
        val camera = VideoCapture(index)

        val resolutions = mutableListOf<Size>()

        for(size in commonResolutions) {
            camera.set(Videoio.CAP_PROP_FRAME_WIDTH, size.width)
            camera.set(Videoio.CAP_PROP_FRAME_HEIGHT, size.height)

            val currentSize = Size(
                camera.get(Videoio.CAP_PROP_FRAME_WIDTH),
                camera.get(Videoio.CAP_PROP_FRAME_HEIGHT),
            )

            if(currentSize.width == 0.0 || currentSize.height == 0.0) {
                continue
            }

            if(!resolutions.contains(currentSize)) {
                resolutions.add(currentSize)
            }
        }

        camera.release()
        return resolutions
    }

}