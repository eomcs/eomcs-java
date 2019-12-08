// Connection-Oriented vs Connectionless
package ch23.f;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {
  public static void main(String[] args) {
    
    // Connection-Oriented = Connectionful
    // => 데이터를 주고 받으려면 항상 먼저 연결해야 한다.
    // => Socket, ServerSocket을 사용하여 처리한다.
    
    try (Socket socket = new Socket("localhost", 8888);
        PrintStream out = new PrintStream(socket.getOutputStream());
        Scanner in = new Scanner(socket.getInputStream())) {
      
      System.out.println("서버와 연결됨!");
      
      // 먼저 연결을 한 다음에 데이터를 보내고 받을 수 있다.
      out.println("Hello!");
      System.out.println("데이터 보냄!");
      
      String response = in.nextLine();
      System.out.println("데이터 받음!");
      
      System.out.println(response);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}











