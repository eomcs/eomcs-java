// 대기열 개수 설정하기
package ch23.a;

import java.net.ServerSocket;
import java.util.Scanner;

public class Server2 {

  public static void main(String[] args) throws Exception {
    Scanner keyboard = new Scanner(System.in);
    
    // 대기열?
    // => 클라이언트가 접속을 요청하면 대기열에 클라이언트 정보를 저장한다.
    // => 저장은 큐(FIFO) 방식으로 관리한다.
    // => 대기열의 크기가 클라이언트의 연결을 허락하는 최대 개수이다.
    // => 대기열을 초과하여 클라이언트 요청을 들어 왔을 때 서버는 응답하지 않는다.
    //    클라이언트는 내부에 설정된 시간(timeout)동안 기다리다 응답을 받지 못하면 
    //    예외를 던지고 연결 요청을 취소한다.
    // => new ServerSocket(포트번호, 대기열크기);
    ServerSocket serverSocket = new ServerSocket(8888, 3);
    
    System.out.println("서버 소켓 생성 완료!");
    System.out.println("클라이언트의 연결을 기다림!");
    
    keyboard.nextLine(); // 사용자가 엔터를 칠 때까지 리턴하지 않는다.
    
    serverSocket.close();
    System.out.println("서버 소켓을 닫음.");
    
    keyboard.close();
  }

}
