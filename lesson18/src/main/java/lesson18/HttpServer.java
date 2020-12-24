package lesson18;

import org.jsoup.Jsoup;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class HttpServer {

    public static void main(String[] args) throws Throwable {
        ServerSocket ss = new ServerSocket(8080);
        while (true) {
            Socket s = ss.accept();
            System.err.println("Клиент подключился");
            new Thread(new SocketProcessor(s)).start();
        }
    }

    private static class SocketProcessor implements Runnable {

        private Socket soket;
        private InputStream input;
        private OutputStream output;

        private SocketProcessor(Socket soket) throws Throwable {
            this.soket = soket;
            this.input = soket.getInputStream();
            this.output = soket.getOutputStream();
        }

        public void run() {
            try {
                String dirFiles = readInputHeaders();
                writeResponse("<html><body>" + dirFiles + "</body></html>");
            } catch (Throwable t) {
            } finally {
                try {
                    soket.close();
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
            System.err.println("Обработка клиента завершена");
        }

        private void writeResponse(String s) throws Throwable {
            String response = getResponse("text/html", s.length());
            String result = response + s;
            output.write(result.getBytes());
            output.flush();
        }

        private String readInputHeaders() throws Throwable {
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            StringBuilder str = new StringBuilder();
            while (true) {
                String s = br.readLine();
                if (s == null || s.trim().length() == 0) {
                    break;
                }
                if (!s.contains("GET")) {
                    notFound();
                    break;
                }
                s = s.replace("GET ", "").replace(" HTTP/1.1", "");
                File dir = new File("C://Users//user//IdeaProjects//Innopolis/" + s);
                File[] files = dir.listFiles();
                if (files == null) {
                    notFound();
                    break;
                }
                List<File> list = Arrays.asList(files);
                list.forEach(x -> str.append(x.getName() + " <br>"));
            }
            return str.toString();
        }

        private void notFound() {
            try {
                String pathForDocker = "dir//index.html";
                File file = new File(pathForDocker);
                byte[] byteFile = Files.readAllBytes(Paths.get(pathForDocker));
                byte[] result = getResponse("text/html", byteFile.length)
                        .concat(Jsoup.parse(file, "UTF-8").toString())
                        .getBytes();
                output.write(result);
                output.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String getResponse(String type, int length) {
            return "HTTP/1.1\r\n" +
                    "Server: Server\r\n" +
                    "Content-Type: " + type + "\r\n" +
                    "Content-Length: " + length + "\r\n" +
                    "Connection: close\r\n\r\n";
        }
    }
}
