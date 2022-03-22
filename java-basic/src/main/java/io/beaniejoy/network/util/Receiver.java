package io.beaniejoy.network.util;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receiver extends Thread {
    Socket socket;
    DataInputStream in;

    public Receiver(Socket socket) {
        this.socket = socket;

        try {
            in = new DataInputStream(socket.getInputStream());
        } catch (Exception ignored) {
        }
    }

    @Override
    public void run() {
        System.out.println("현재 포트: " + socket.getLocalPort());

        // socket 연결 끊긴 상황에서 in.readUTF 호출하면 IOException 발생
        try {
            while (in != null) {
                System.out.println(in.readUTF());
            }
        } catch (IOException ignored) {
            System.out.println("IOException");
        } finally {
            System.out.println("상대방과 연결이 끊겼습니다. (Receiver)");
        }
    }
}
