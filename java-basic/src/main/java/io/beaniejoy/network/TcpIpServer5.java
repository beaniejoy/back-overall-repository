package io.beaniejoy.network;

import io.beaniejoy.network.util.Receiver;
import io.beaniejoy.network.util.Sender;

import java.net.ServerSocket;
import java.net.Socket;

public class TcpIpServer5 {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(7777);
            System.out.println("서버 준비가 완료되었습니다.");

            // ServerSocket 은 대기하고 있는중
            // Client 로부터 요청이 오면 Socket 을 새로 생성해 Client Socket 과 연결시켜줌
            Socket socket = serverSocket.accept();

            System.out.println("클라이언트와 연결이 되었습니다. [" + socket.getPort() + "]");

            // 서버쪽에 클라이언트와 연결된 socket 을 sender 와 receiver 에게 전달
            // sender 는 서버쪽에서 클라로 데이터를 보내는 thread
            // receiver 는 클라에서 데이터를 받는 thread
            Sender sender = new Sender(socket);
            Receiver receiver = new Receiver(socket);

            sender.start();
            receiver.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
