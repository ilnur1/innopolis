package lesson8.chat.Server;

import lesson8.chat.ServerThread.ServerThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
    private static final int PORT = 8080;
    private static LinkedList<ServerThread> serverList = new LinkedList<>();

    public static int getPORT() {
        return PORT;
    }

    public static LinkedList<ServerThread> getServerList() {
        return serverList;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        try {
            while (true) {
                Socket socket = server.accept();
                try {
                    serverList.add(new ServerThread(socket));
                    System.out.println("Подключился клиент. Всего:" + serverList.size());
                } catch (IOException e) {
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }
}
