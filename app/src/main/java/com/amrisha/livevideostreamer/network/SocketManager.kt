package com.amrisha.livevideostreamer.network

import java.io.DataOutputStream
import java.net.Socket

class SocketManager {

    private var socket: Socket? = null
    private var outputStream: DataOutputStream? = null

    fun connect(ip: String, port: Int): Boolean {

        return try {

            socket = Socket(ip, port)

            outputStream = DataOutputStream(socket!!.getOutputStream())

            true

        } catch (e: Exception) {

            android.util.Log.e("SocketError", "Connection failed", e)

            false
        }
    }

    fun sendFrame(frame: ByteArray) {

        try {

            outputStream?.writeInt(frame.size)

            outputStream?.write(frame)

            outputStream?.flush()

        } catch (e: Exception) {

        android.util.Log.e(
            "SocketManager",
            "Failed to send frame",
            e
        )
    }
    }

    fun disconnect() {

        try {

            outputStream?.close()
            socket?.close()

        } catch (e: Exception) {

            e.printStackTrace()
        }
    }
}