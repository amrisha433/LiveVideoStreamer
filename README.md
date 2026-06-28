# 📹 Live Video Streamer

A real-time Android video streaming application built using **Kotlin**, **Jetpack Compose**, and **CameraX**. The application captures live camera frames from an Android device, compresses them into JPEG format, and streams the frames over a TCP socket to a desktop Python receiver running on the same local network.

The desktop receiver accepts incoming connections, reconstructs the transmitted frames, decodes them using OpenCV, and displays the live video stream in real time.

---

# ✨ Features

* 📷 Live Camera Preview using CameraX
* 🎥 Rear Camera Support
* 🔐 Runtime Camera Permission Handling
* 🔄 Continuous Frame Capture
* 🖼 JPEG Frame Compression
* 🌐 TCP Socket Communication
* 📡 Configurable Receiver IP Address
* 🔌 Configurable Port Number
* 📶 Real-Time Connection Status
* 💻 Desktop Receiver using Python
* 🖥 Live Video Display with OpenCV
* ⚡ Lightweight Local Network Streaming

---

# 🛠 Tech Stack

### Android

* Kotlin
* Jetpack Compose
* CameraX
* AndroidX
* Kotlin Coroutines
* Material 3

### Desktop Receiver

* Python 3
* OpenCV
* NumPy
* Pillow
* Python Socket Programming

---

# 🧰 Tools & Technologies

| Category          | Technology      |
| ----------------- | --------------- |
| Language          | Kotlin, Python  |
| UI Toolkit        | Jetpack Compose |
| Camera            | CameraX         |
| Networking        | TCP Socket      |
| Image Processing  | OpenCV          |
| Image Compression | JPEG            |
| IDE               | Android Studio  |
| Version Control   | Git             |
| Repository        | GitHub          |
| Build Tool        | Gradle          |
| Operating System  | Android         |
| Desktop Platform  | Python          |

---

# 📂 Project Structure

```text
LiveVideoStreamer
│
├── app/
│   ├── src/
│   └── PythonReceiver/
│       ├── Receiver.py
│       └── requirements.txt
│
├── screenshots/
│   ├── home_screen.png
│   ├── connection_screen.png
│   ├── streaming_screen.png
│   └── desktop_receiver.png
│
├── demo/
│   └── LiveVideoStreamerDemo.mp4
│
└── README.md
```

---

# 🏗 Architecture

```text
Android Camera
       │
       ▼
CameraX Preview
       │
       ▼
ImageAnalysis
       │
       ▼
ImageProxy
       │
       ▼
JPEG Compression
       │
       ▼
TCP Socket
       │
════════ Local Wi-Fi ════════
       │
       ▼
Python Receiver
       │
       ▼
OpenCV Decoder
       │
       ▼
Desktop Display
```

---

# 📋 Requirements

## Android

* Android 8.0 (API 26) or above
* Camera Permission

## Desktop

* Python 3.10+
* OpenCV
* NumPy
* Pillow

---

# 🚀 Installation

## Clone Repository

```bash
git clone https://github.com/YOUR_USERNAME/LiveVideoStreamer.git
```

---

## Install Python Dependencies

```bash
cd app/PythonReceiver

pip install -r requirements.txt
```

---

# ▶ Running the Desktop Receiver

```bash
cd app/PythonReceiver

python3 Receiver.py
```

Expected Output

```text
Waiting for Android connection...
```

---

# 📱 Running the Android Application

1. Open the project in Android Studio.
2. Build and install the application on an Android device.
3. Ensure both the Android device and desktop are connected to the same Wi-Fi network.
4. Launch the Python receiver.
5. Enter:

   * Receiver IP Address
   * Port Number
6. Tap **Connect**.
7. The live video stream will appear on the desktop receiver.

---

# 📸 Screenshots

## Android Application

| Home Screen                      | Connection Screen                      |
| -------------------------------- | -------------------------------------- |
| ![](screenshots/home_screen.png) | ![](screenshots/connection_screen.png) |

## Streaming

| Android Streaming                     | Desktop Receiver                      |
| ------------------------------------- | ------------------------------------- |
| ![](screenshots/streaming_screen.png) | ![](screenshots/desktop_receiver.png) |

---

# 🎥 Demo Video

A complete demonstration of the project is included in the repository.

```text
demo/
└── LiveVideoStreamerDemo.mp4
```

Or add a YouTube (Unlisted) or Google Drive link here:

**Demo Video:** `<Paste Demo Video Link Here>`

The demonstration includes:

* Launching the Android application
* Starting the Python receiver
* Connecting both devices
* Live video streaming
* Disconnecting the session

---

# 📊 Performance

* JPEG Compression Quality: 70%
* TCP Socket Communication
* Continuous Frame Streaming
* Low Latency Streaming over Local Network

---

# ⚠ Current Limitations

* Supports a single receiver connection.
* Works only over the same local network.
* Video only (no audio streaming).
* Uses JPEG compression instead of H.264 encoding.

---

# 🚀 Future Improvements

* H.264 Video Encoding using MediaCodec
* RTSP Streaming Support
* Adaptive Bitrate Streaming
* Audio Streaming
* Multi-client Support
* Recording Functionality
* Dynamic Resolution Selection

---

# 👨‍💻 Author

**Amrisha Maurya**

Android Developer | Kotlin | Jetpack Compose | CameraX

GitHub: https://github.com/amrisha433
