package socketio;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

  private static final String CRLF = "\r\n";

  public static void main(String[] args) throws UnknownHostException {
    ExecutorService executorService = Executors.newFixedThreadPool(50);
    InetAddress inetAddress = InetAddress.getLoopbackAddress();
    System.out.println("inetAddress = " + inetAddress);
    try (ServerSocket serverSocket = new ServerSocket(9999, 50, inetAddress);) {

      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("socket = " + socket);
        executorService.submit(() -> {
          InputStream in = null;
          try (
            OutputStream raw = socket.getOutputStream();
            Writer out = new OutputStreamWriter(raw, StandardCharsets.UTF_8)) {

            in = socket.getInputStream();

            System.out.println("========================");
            String inputData = getInputData(in);
            System.out.println("sb.toString() = " + inputData);

            String data = "Test!!";

            out.write("HTTP/1.1 200 OK");
            out.write("Host: localhost".concat(CRLF));
            out.write("Content-length: " + data.getBytes().length + CRLF);
            out.write("Content-Type: text/html" + CRLF + CRLF);

            out.write(data);
            out.flush();
          } catch (IOException e) {
            e.printStackTrace();
          } finally {
            try {
              in.close();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        });
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static String getInputData(InputStream in) throws IOException {
    StringBuilder sb = new StringBuilder();
    while (true) {
      int read = in.read();
      if (in.available() == 0 || in.available() == -1) {
        break;
      }
      sb.append((char) read);
    }
    return sb.toString();
  }
}
