package com.amrisha.livevideostreamer.network

import java.io.OutputStream
import java.net.Socket

class SocketManager {

    private var socket: Socket? = null
    private var outputStream: OutputStream? = null

    fun connect(
        ip: String,
        port: Int
    ): Boolean {

        return try {

            socket = Socket(ip, port)

            outputStream = socket?.getOutputStream()

            true

        } catch (e: Exception) {

            e.printStackTrace()

            false

        }

    }

    fun send(data: ByteArray) {

        try {

            outputStream?.write(data)
            outputStream?.flush()

        } catch (e: Exception) {
            e.printStackTrace()
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