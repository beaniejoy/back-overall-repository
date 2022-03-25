package io.beaniejoy.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class TcpIpMultiChatClient {
    public static void main(String[] args) {
        System.out.println("대화명을 입력하세요.");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
//        scanner.close();

        try {
            String serverIp = "127.0.0.1";

            Socket socket = new Socket(serverIp, 7777);
            System.out.println("서버에 연결되었습니다.");

            Thread sender = new Thread(new ClientSender(socket, name));
            Thread receiver = new Thread(new ClientReceiver(socket));

            sender.start();
            receiver.start();
        } catch (ConnectException e) {
            e.printStackTrace();
        } catch (Exception ignored) {
        }
    }

    // 서버에 전송하는 역할
    static class ClientSender extends Thread{
        Socket socket;
        DataOutputStream out;
        String name;

        ClientSender(Socket socket, String name) {
            this.socket = socket;

            try {
                out = new DataOutputStream(socket.getOutputStream());
                this.name = name;
            } catch (Exception ignored) {
            }
        }

        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            try {
                if (out != null) {
                    out.writeUTF(name);
                }
                while (out != null) {
                    out.writeUTF("[" + name + "] " + scanner.nextLine());
                }
            } catch (IOException ignored) {
            }
        }
    }

    // 서버에서 수신하는 역할
    static class ClientReceiver extends Thread {
        Socket socket;
        DataInputStream in;

        ClientReceiver(Socket socket) {
            this.socket = socket;
            try {
                in = new DataInputStream(socket.getInputStream());
            } catch (IOException ignored) {
            }
        }

        @Override
        public void run() {
            while (in != null) {
                try {
                    // in.readUTF() 여기서 서버로부터 메시지가 올 때까지 기다림
                    System.out.println(in.readUTF());
                } catch (IOException ignored) {
                    // FIXME 서버 어플리케이션 종료시 exception 무한 루프로 계속 돌게 됨
                    // 서버가 다시 실행되어도 이미 thread 는 계속 실행중
                    System.out.println("exception");
                }
            }
        }
    }

}
