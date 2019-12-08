// 데이터 주고 받기 - 서버 만들기
package ch23.b;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server1 {

  public static void main(String[] args) {
    try (Scanner keyboard = new Scanner(System.in);
        ServerSocket serverSocket = new ServerSocket(8888)) {
      
      System.out.println("클라이언트의 연결을 기다리고 있음.");
      
      try (
        // 대기열에서 클라이언트를 한 개 꺼낸다.
        // - 대기열에 클라이언트 정보가 없다면 있을 때까지 기다린다. 리턴하지 않는다.
        //   즉 blocking 모드로 작동한다.
        Socket socket = serverSocket.accept();
        
        // 클라이언트와 데이터를 주고 받을 입출력 스트림 객체를 준비한다.
        // => 출력 스트림 객체를 준비하기
        OutputStream out = socket.getOutputStream();
        
        // => 입력 스트림 객체를 준비하기
        InputStream in = socket.getInputStream()) {
        
        System.out.println("대기열에서 클라이언트 정보를 꺼내 소켓을 생성하였음.");
        System.out.println("클라이언트와 통신할 입출력 스트림이 준비되었음.");
        
        // Client1과 Server1의 통신 규칙에 따라 순서대로 입출력 해야 한다.
        // 왜? 입출력은 blocking 모드로 작동하기 때문이다.
        // 클라이언트와 서버 간의 데이터를 주고 받는 통신 규칙을 "프로토콜(protocol)"이라 한다.
        // 에코(echo)는 클라이언트에서 한 줄의 문자열을 보낸다.
        // 따라서 서버는 한 줄의 문자열을 읽은 후에 응답해야 한다.
        
        // 서버에서 한 줄의 데이터를 읽기 전에는 클라이언트의 write()가 리턴하지 않음을 
        // 확인하기 위해 클라이언트가 보낸 데이터를 읽지 않도록 잠시 실행을 중단한다.
        System.out.print("데이터를 읽기 전에 잠깐!");
        keyboard.nextLine(); // 사용자가 콘솔창에서 엔터를 칠 때까지 리턴하지 않는다.
        
        // => 클라이언트가 1바이트를 때까지 리턴하지 않는다.
        int request = in.read(); // blocking 모드로 작동한다.
        System.out.println(request);

        // 서버가 데이터를 보내지 않으면 클라이언트의 read()는 리턴하지 않는다.
        // 이를 확인하기 위해 잠시 실행을 멈춘다.
        System.out.print("데이터를 보내기 전에 잠깐!");
        keyboard.nextLine();
        
        // => 클라이언트에게 받은 문자열을 그대로 보낸다.
        //    물론 클라이언트가 보낸 데이터를 다 읽을 때까지 리턴하지 않는다.
        out.write(request);
        System.out.println("클라인트에게 데이터를 보냈음.");
        
      }
      System.out.println("클라이언트와의 연결을 끊었음.");
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("서버 종료!");
  }

}
