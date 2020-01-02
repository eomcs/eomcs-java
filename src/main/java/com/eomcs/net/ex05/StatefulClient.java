// Stateless와 Stateful 의 비교 
package com.eomcs.net.ex05;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

// Stateful 방식의 특징
// => 클라이언트와 계속 연결을 유지하기 때문에 작업 결과를 계속 서버에 유지할 수 있다.
// 
public class StatefulClient {
  public static void main(String[] args) throws Exception {
    try (
        Scanner keyScan = new Scanner(System.in);

        // 합계 계산을 요청하는 클라이언트를 만들어보자!
        Socket socket = new Socket("localhost", 8888);
        PrintStream out = new PrintStream(socket.getOutputStream());
        Scanner in = new Scanner(socket.getInputStream())) {

      while (true) {
        System.out.print("값? ");
        String str = keyScan.nextLine(); // 키보드에서 값을 입력 받아

        out.println(str); // 서버에 보낸다. 
        if (str.equals("")) { // 만약 값을 입력하지 않았으면 
          System.out.println(in.nextLine()); // 서버가 보낸 결과를 출력한다.
          break;
        }
      }
    }
  }
}






