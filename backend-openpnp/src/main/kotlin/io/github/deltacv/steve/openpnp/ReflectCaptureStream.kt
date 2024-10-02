package io.github.deltacv.steve.openpnp

import com.sun.jna.Memory
import com.sun.jna.Pointer
import org.openpnp.capture.CaptureStream

class ReflectCaptureStream(
    val captureStream: CaptureStream
) {
    companion object {
        val contextField by lazy {
            CaptureStream::class.java.getDeclaredField("context").apply {
                isAccessible = true
            }
        }

        val memoryField by lazy {
            CaptureStream::class.java.getDeclaredField("memory").apply {
                isAccessible = true
            }
        }
    }

    val context get() = contextField.get(captureStream) as Pointer

    val memory get() = memoryField.get(captureStream) as Memory

}