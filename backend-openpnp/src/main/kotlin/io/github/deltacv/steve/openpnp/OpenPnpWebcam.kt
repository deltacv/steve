package io.github.deltacv.steve.openpnp

import io.github.deltacv.steve.WebcamBase
import io.github.deltacv.steve.WebcamRotation
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.imgproc.Imgproc
import org.openpnp.capture.CaptureDevice
import org.openpnp.capture.CaptureException
import org.openpnp.capture.CaptureStream
import org.openpnp.capture.library.OpenpnpCaptureLibrary

class OpenPnpWebcam(
    private val device: CaptureDevice,
    override val index: Int,
    rotation: WebcamRotation = WebcamRotation.UPRIGHT
) : WebcamBase(rotation) {

    private var stream: CaptureStream? = null
    private var reflectStream: ReflectCaptureStream? = null

    override val isOpen: Boolean
        get() = stream != null

    override var resolution = supportedResolutions.getOrElse(0) {
        throw IllegalStateException("No supported resolutions found for ${device.name}")
    }
        set(value) {
            if(stream != null) {
                throw IllegalStateException("Cannot change resolution while camera is open")
            }

            field = value
        }

    override val supportedResolutions: List<Size>
        get() {
            val res = mutableListOf<Size>()

            for(format in device.formats) {
                res.add(Size(
                    format.formatInfo.width.toDouble(),
                    format.formatInfo.height.toDouble()
                ))
            }

            return res
        }

    override var fps: Double
        get() = stream?.format?.formatInfo?.fps?.toDouble() ?: 0.0
        set(_) {
            throw UnsupportedOperationException("Setting FPS is not supported by OpenPnp")
        }

    override val name get() = device.name

    override val propertyControl get() = OpenPnpWebcamPropertyControl(stream ?: throw IllegalStateException("Camera is not open"))

    private var bytes = ByteArray(0)

    override fun internalRead(mat: Mat) {
        if(mat.size() != resolution) {
            mat.create(resolution, CvType.CV_8UC3)
        }

        val safeStream = reflectStream ?: throw IllegalStateException("Camera is not open")

        val context = safeStream.context
        val memory = safeStream.memory

        val result = OpenpnpCaptureLibrary.INSTANCE.Cap_captureFrame(
            context,
            safeStream.captureStream.streamId,
            memory, memory.size().toInt()
        )

        if (result != OpenpnpCaptureLibrary.CAPRESULT_OK) {
            throw CaptureException(result);
        }

        if(bytes.size != memory.size().toInt()) {
            bytes = ByteArray(memory.size().toInt())
        }

        memory.read(0, bytes, 0, bytes.size)

        mat.put(0, 0, bytes)
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2RGB)
    }

    override fun open() {
        for(format in device.formats) {
            if(format.formatInfo.width == resolution.width.toInt() &&
                format.formatInfo.height == resolution.height.toInt()) {
                stream = device.openStream(format)
                reflectStream = ReflectCaptureStream(stream!!)
                return
            }
        }

        val supportedResolutionsStr = supportedResolutions.joinToString(", ") { "[${it.width}x${it.height}]" }

        throw IllegalArgumentException("Resolution $resolution is not supported by ${device.name}. Supported resolutions: $supportedResolutionsStr")
    }

    override fun close() {
        stream?.close()
        stream = null
    }

}