@file:JvmName("WebcamUtil")

package io.github.deltacv.steve

import org.opencv.core.Size

val commonResolutions = listOf(
    Size(176.0, 144.0),
    Size(320.0, 240.0),
    Size(640.0, 360.0),
    Size(640.0, 480.0),
    Size(960.0, 540.0),
    Size(1024.0, 768.0),
    Size(1280.0, 720.0),
    Size(1280.0, 1024.0)
)