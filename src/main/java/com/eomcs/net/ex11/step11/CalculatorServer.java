// 계산기 서버 만들기 - 11단계: Stateful 방식을 Stateless 방식으로 전환
// - 클라이언트 한 번 접속에 한 번만 요청만 처리한다.
// 
package com.eomcs.net.ex11.step11;

import java.net.ServerSocket;

public class CalculatorServer {
  public static void main(String[] args) {

    try (ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("서버 실행 중...");

      RequestProcessor requestProcessor = new RequestProcessor();

      while (true) {
        requestProcessor.setSocket(serverSocket.accept());
        requestProcessor.service();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}
