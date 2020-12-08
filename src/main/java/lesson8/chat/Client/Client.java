package lesson8.chat.Client;

import java.io.*;
import java.net.Socket;

public class Client {
    private static Socket socket;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;
    public static void main(String[] args) {
        try {
            try {
                socket = new Socket("localhost", 8080);
                reader = new BufferedReader(new InputStreamReader(System.in));
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                System.out.println("Введите свое имя:");
                String name = reader.readLine();
                out.write(name+"\n");
                out.flush();
                System.out.println("Теперь вы можете вводить сообщения");
                while (true) {
                    String word = reader.readLine();
                    if(word.equals("exit")) break;
                    out.write(word + "\n");
                    out.flush();
                    String serverWord = in.readLine();
                    System.out.println(serverWord);
                }
            } finally {
                System.out.println("Клиент был закрыт...");
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }
}
