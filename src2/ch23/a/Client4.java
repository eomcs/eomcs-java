// 대기열에서 클라이언트 연결 정보를 꺼내 처리하기
package ch23.a;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class Client4 {

  // 예제 테스트
  // => Server4 를 먼저 실행시킨 다음에 이 클래스를 여러 번 실행하라!
  // 
  public static void main(String[] args) throws Exception {
    Scanner keyboard = new Scanner(System.in);
    
    Socket socket = new Socket();
    System.out.println("소켓 생성됨.");
    
    SocketAddress socketAddress = new InetSocketAddress("localhost", 8888);
    
    System.out.println("서버와 연결 중...");
    socket.connect(socketAddress, 40000); // timeout : milliseconds
    System.out.println("서버와 연결되었음!");
    
    keyboard.nextLine(); // 사용자가 엔터를 칠 때까지 다음 코드로 이동하지 않는다.
    
    socket.close();
    System.out.println("서버와의 연결을 끊었음.");
    
    keyboard.close();
  }

}
