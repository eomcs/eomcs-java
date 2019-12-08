// 데이터 주고 받기 - 에코(echo) 클라이언트 만들기
package ch23.b;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {

  public static void main(String[] args) {
    
    try (Scanner keyboard = new Scanner(System.in);
        Socket socket = new Socket("localhost", 8888);
        // 문자열을 주고 받기 편하도록 오리지널 입출력 스트림 객체에 데코레이터를 붙인다.
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        Scanner in = new Scanner(socket.getInputStream())) {
      
      System.out.println("서버와 연결되었음!");
      
      System.out.println("서버에 데이터를 보내기 전에 잠깐!");
      keyboard.nextLine();
      
      out.println("Hello!");
      out.flush(); // 스트림 객체의 내부 버퍼에 출력된 내용을 네트워크로 방출시킨다.
      
      System.out.println("서버의 데이터를 받기를 기다림!");
      String response = in.nextLine();
      System.out.println(response);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}








