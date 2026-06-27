package com.amrisha.livevideostreamer.camera

import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.amrisha.livevideostreamer.network.SocketManager
import com.amrisha.livevideostreamer.utils.ImageUtils
import java.util.concurrent.atomic.AtomicBoolean

class FrameAnalyzer(
    private val socketManager: SocketManager
) : ImageAnalysis.Analyzer {

    private val isProcessing = AtomicBoolean(false)

    override fun analyze(image: ImageProxy) {

        if (!isProcessing.compareAndSet(false, true)) {
            image.close()
            return
        }

        try {

            Log.d("FrameAnalyzer", "Frame received")

            val frameBytes = ImageUtils.imageProxyToJpegByteArray(image)

            Log.d("FrameAnalyzer", "JPEG Size = ${frameBytes.size}")

            socketManager.sendFrame(frameBytes)

            Log.d("FrameAnalyzer", "Frame sent")

        } catch (e: Exception) {

            Log.e("FrameAnalyzer", "Error sending frame", e)

        } finally {

            image.close()
            isProcessing.set(false)

        }
    }
}