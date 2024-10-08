/*
 * Copyright (c) 2021 Sebastian Erives
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package io.github.deltacv.steve

import io.github.deltacv.steve.opencv.OpenCvWebcamBackend
import org.opencv.core.Mat
import org.opencv.core.Size

interface Webcam {

    companion object {
        var backend: WebcamBackend = OpenCvWebcamBackend

        val availableWebcams get() = backend.availableWebcams
    }

    val isOpen: Boolean

    var resolution: Size
    val supportedResolutions: List<Size>

    val supportedResolutionsString get() = supportedResolutions.joinToString(", ") { "[${it.width}x${it.height}]" }

    var rotation: WebcamRotation
    var fps: Double

    val index: Int
    val name: String

    val propertyControl: WebcamPropertyControl

    fun open()

    fun read(mat: Mat)

    fun close()

}