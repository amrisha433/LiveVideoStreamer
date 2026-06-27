import socket
import struct
import cv2
import numpy as np

HOST = "0.0.0.0"
PORT = 9999

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind((HOST, PORT))
server.listen(1)

print("Waiting for Android connection...")

client, address = server.accept()

print("Connected:", address)


def receive_exact(sock, size):
    data = b""

    while len(data) < size:
        packet = sock.recv(size - len(data))

        if not packet:
            return None

        data += packet

    return data


while True:

    size_bytes = receive_exact(client, 4)

    if size_bytes is None:
        break

    frame_size = struct.unpack(">I", size_bytes)[0]

    frame_bytes = receive_exact(client, frame_size)

    if frame_bytes is None:
        break

    frame = cv2.imdecode(
        np.frombuffer(frame_bytes, dtype=np.uint8),
        cv2.IMREAD_COLOR
    )

    if frame is None:
        continue

    cv2.imshow("Android Stream", frame)

    if cv2.waitKey(1) & 0xFF == ord("q"):
        break

client.close()
server.close()
cv2.destroyAllWindows()
