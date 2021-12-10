package io.github.deltacv.steve.openimaj

import org.bridj.Pointer
import org.opencv.core.Size
import org.openimaj.video.capture.VideoCapture

class ReflectOpenIMAJGrabber(private val videoCapture: VideoCapture) {

    private val clazz = VideoCapture::class.java

    private val grabberField by lazy { clazz.getDeclaredField("grabber") }

    private val grabberClazz by lazy { grabber::class.java }

    private val grabber by lazy {
        grabberField.isAccessible = true
        grabberField.get(videoCapture)
    }

    private val nextFrameMethod by lazy {
        grabberClazz.getDeclaredMethod("nextFrame").apply { isAccessible = true }
    }
    private val getImageMethod by lazy {
        grabberClazz.getDeclaredMethod("getImage").apply { isAccessible = true }
    }

    private val getWidthMethod by lazy {
        grabberClazz.getDeclaredMethod("getWidth").apply { isAccessible = true }
    }
    private val getHeightMethod by lazy {
        grabberClazz.getDeclaredMethod("getHeight").apply { isAccessible = true }
    }

    @Suppress("UNCHECKED_CAST")
    val image: Pointer<Byte>? get() {
        val img = getImageMethod.invoke(grabber)
        return if(img is Pointer<*>) img as Pointer<Byte> else null
    }

    val resolution get() = Size(
        (getWidthMethod.invoke(grabber) as Int).toDouble(),
        (getHeightMethod.invoke(grabber) as Int).toDouble()
    )

    fun nextFrame() = nextFrameMethod.invoke(grabber) as Int

}