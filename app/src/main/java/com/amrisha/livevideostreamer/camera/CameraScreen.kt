package com.amrisha.livevideostreamer.camera

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.amrisha.livevideostreamer.network.SocketManager

@Composable
fun CameraScreen() {

    val context = LocalContext.current

    var hasPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->
            hasPermission = granted
        }

    LaunchedEffect(Unit) {
        if (!hasPermission) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    var ipAddress by remember {
        mutableStateOf("")
    }

    var port by remember {
        mutableStateOf("")
    }

    var status by remember {
        mutableStateOf("Disconnected")
    }

    val socketManager = remember {
        SocketManager()
    }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        if (hasPermission) {

            CameraPreview(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

        } else {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text("Camera Permission Required")
            }

        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = ipAddress,
            onValueChange = { ipAddress = it },
            label = {
                Text("IP Address")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = port,
            onValueChange = { port = it },
            label = {
                Text("Port")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {

                scope.launch {

                    status = "Connecting..."

                    val connected = withContext(Dispatchers.IO) {

                        socketManager.connect(
                            ipAddress,
                            port.toIntOrNull() ?: 0
                        )

                    }

                    status =
                        if (connected)
                            "Connected"
                        else
                            "Connection Failed"

                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text("Connect")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text("Start Streaming")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {

                socketManager.disconnect()

                status = "Disconnected"

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text("Disconnect")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Status : $status",
            modifier = Modifier.padding(16.dp)
        )

    }
}