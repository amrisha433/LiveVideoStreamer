package com.amrisha.livevideostreamer.camera

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy

class FrameAnalyzer(
    private val onFrameCaptured: (ImageProxy) -> Unit
) : ImageAnalysis.Analyzer {

    override fun analyze(image: ImageProxy) {
        onFrameCaptured(image)
    }
}