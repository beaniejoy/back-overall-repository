package io.beaniejoy.network.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Sender extends Thread {
    private DataOutputStream out;
    private String name;

    public Sender(Socket socket) {
        try {
            out = new DataOutputStream(socket.getOutputStream());
            name = "[" + socket.getInetAddress() + ":" + socket.getLocalPort() + "]";
        } catch (Exception ignored) {
        }
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        // 클라이언트에서 애플리케이션 종료 후
        // out 에 대해서 접근할 때 IOException 발생
        try {
            while (out != null)
                out.writeUTF(name + scanner.nextLine());
        } catch (IOException ignored) {

        } finally {
            System.out.println("상대방과 연결이 끊겼습니다. (Sender)");
        }

    }
}
