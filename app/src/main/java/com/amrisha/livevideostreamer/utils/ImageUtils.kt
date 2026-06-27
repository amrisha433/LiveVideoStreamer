package com.amrisha.livevideostreamer.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.graphics.Matrix
import android.graphics.Rect
import android.graphics.YuvImage
import androidx.camera.core.ImageProxy
import java.io.ByteArrayOutputStream

object ImageUtils {

    fun imageProxyToJpegByteArray(
        image: ImageProxy,
        jpegQuality: Int = 70
    ): ByteArray {

        val bitmap = imageProxyToBitmap(image)

        val rotatedBitmap = rotateBitmap(
            bitmap,
            image.imageInfo.rotationDegrees.toFloat()
        )

        val outputStream = ByteArrayOutputStream()
        rotatedBitmap.compress(
            Bitmap.CompressFormat.JPEG,
            jpegQuality,
            outputStream
        )

        return outputStream.toByteArray()
    }

    private fun imageProxyToBitmap(image: ImageProxy): Bitmap {

        val nv21 = yuv420888ToNv21(image)

        val yuvImage = YuvImage(
            nv21,
            ImageFormat.NV21,
            image.width,
            image.height,
            null
        )

        val out = ByteArrayOutputStream()

        yuvImage.compressToJpeg(
            Rect(0, 0, image.width, image.height),
            100,
            out
        )

        val imageBytes = out.toByteArray()

        return BitmapFactory.decodeByteArray(
            imageBytes,
            0,
            imageBytes.size
        )
    }

    private fun rotateBitmap(bitmap: Bitmap, degrees: Float): Bitmap {

        if (degrees == 0f) return bitmap

        val matrix = Matrix()
        matrix.postRotate(degrees)

        return Bitmap.createBitmap(
            bitmap,
            0,
            0,
            bitmap.width,
            bitmap.height,
            matrix,
            true
        )
    }

    private fun yuv420888ToNv21(image: ImageProxy): ByteArray {

        val yBuffer = image.planes[0].buffer
        val uBuffer = image.planes[1].buffer
        val vBuffer = image.planes[2].buffer

        val ySize = yBuffer.remaining()
        val uSize = uBuffer.remaining()
        val vSize = vBuffer.remaining()

        val nv21 = ByteArray(ySize + uSize + vSize)

        yBuffer.get(nv21, 0, ySize)
        vBuffer.get(nv21, ySize, vSize)
        uBuffer.get(nv21, ySize + vSize, uSize)

        return nv21
    }
}