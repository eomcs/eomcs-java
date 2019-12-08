// Connection-Oriented vs Connectionless
package ch23.f;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server1 {
  public static void main(String[] args) {

    // Connection-Oriented = Connectionful
    // => 데이터를 주고 받으려면 항상 먼저 연결해야 한다.
    // => Socket, ServerSocket을 사용하여 처리한다.

    try (ServerSocket ss = new ServerSocket(8888)) {

      while (true) {
        try (Socket socket = ss.accept();
            PrintStream out = new PrintStream(socket.getOutputStream());
            Scanner in = new Scanner(socket.getInputStream())) {

          // 먼저 연결을 한 다음에 데이터를 보내고 받을 수 있다.
          System.out.println("클라이언트와 연결됨!");

          String request = in.nextLine();
          System.out.println("데이터 받음!");

          out.println("**" + request + "**");
          System.out.println("데이터 보냄!");

        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}











