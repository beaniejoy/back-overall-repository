package io.beaniejoy.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TcpIpMultiChatServer {
    Map<String, DataOutputStream> clients;

    public TcpIpMultiChatServer() {
        clients = new HashMap<>();
        // ConcurrentHashMap 사용하는 방법도 있음
        Collections.synchronizedMap(clients);
    }

    public void start() {
        ServerSocket serverSocket = null;
        Socket socket = null;

        try {
            serverSocket = new ServerSocket(7777);
            System.out.println("서버가 시작되었습니다.");

            while (true) {
                socket = serverSocket.accept();
                System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] 에서 접속하였습니다.");

                ServerReceiver thread = new ServerReceiver(socket);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void sendToAll(String msg) {
        Iterator<String> it = clients.keySet().iterator();
        while (it.hasNext()) {
            try {
                DataOutputStream out = clients.get(it.next());
                out.writeUTF(msg);
            } catch (IOException ignored) {
            }
        }
    }

    public static void main(String[] args) {
        new TcpIpMultiChatServer().start();
    }

    class ServerReceiver extends Thread {
        Socket socket;
        DataInputStream in;
        DataOutputStream out;

        ServerReceiver(Socket socket) {
            this.socket = socket;

            try {
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
            } catch (IOException ignored) {
            }
        }

        @Override
        public void run() {
            String name = "";

            try {
                name = in.readUTF();
                sendToAll("#" + name + "님이 입장하셨습니다.");

                clients.put(name, out);
                System.out.println("현재 서버 접속자 수는 " + clients.size() + "명 입니다.");

                while (in != null) {
                    // in.readUTF() 여기서 클라이언트로부터 메시지가 올 때까지 기다림
                    sendToAll(in.readUTF());
                }
            } catch (IOException ignored) {
            } finally {
                sendToAll("#" + name + "님이 퇴장하셨습니다.");
                clients.remove(name);
                System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "] 에서 접속을 종료하였습니다.");
                System.out.println("현재 서버 접속자 수는 " + clients.size() + "명 입니다.");
            }
        }
    }

}
