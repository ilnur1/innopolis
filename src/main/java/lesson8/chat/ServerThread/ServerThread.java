package lesson8.chat.ServerThread;

import lesson8.chat.Server.Server;
import lesson8.chat.User.User;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private User user;
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    public ServerThread(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }

    /**
     * отправка сообщения
     * @param msg сообщение
     */
    private void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException ignored) {
        }
    }

    /**
     * Отправка сообщений всем подключенным клиентам
     * @param messange сообщение
     */
    private void sendMessange(String messange) {
        if (messange == null)
            return;
        for (ServerThread st : Server.getServerList()) {
            st.send(user.getName() + ": " + messange);
        }
    }

    @Override
    public void run() {
        String word;
        try {
            word = in.readLine();
            user = new User(word);
            while (true) {
                word = in.readLine();
                sendMessange(word);
            }

        } catch (IOException e) {
        }
    }
}
