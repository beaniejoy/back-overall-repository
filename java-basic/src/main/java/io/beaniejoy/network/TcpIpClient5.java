package io.beaniejoy.network;

import io.beaniejoy.network.util.Receiver;
import io.beaniejoy.network.util.Sender;

import java.net.Socket;

public class TcpIpClient5 {
    public static void main(String[] args) {
        try {
            String serverIp = "127.0.0.1";

            Socket socket = new Socket(serverIp, 7777);

            System.out.println("서버에 연결되었습니다.");

            Sender sender = new Sender(socket);
            Receiver receiver = new Receiver(socket);

            sender.start();
            receiver.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
