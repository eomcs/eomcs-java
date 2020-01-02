// Stateless와 Stateful 의 비교 
package com.eomcs.net.ex05;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

// stateless 방식의 특징
// => 요청 할 때마다 서버와 연결한다. 응답 받은 후 서버와의 연결을 끊는다. 
// 
public class StatelessClient {
  public static void main(String[] args) throws Exception {
    // 합계 계산을 요청하는 클라이언트를 만들어보자!
    try (Scanner keyScan = new Scanner(System.in)) {
      while (true) {
        System.out.print("값? ");
        String str = keyScan.nextLine(); // 키보드에서 값을 입력 받아
        try (
            Socket socket = new Socket("localhost", 8888);
            PrintStream out = new PrintStream(socket.getOutputStream());
            Scanner in = new Scanner(socket.getInputStream())) {

          out.println(str); // 서버에 보낸다. 
          if (str.equals("")) { // 만약 값을 입력하지 않았으면 
            System.out.println(in.nextLine()); // 서버가 보낸 결과를 출력한다.
            break;
          }
        }
      }
    }
  }

  static void processRequest(Socket socket) throws Exception {
    try (
        Socket socket2 = socket;
        PrintStream out = new PrintStream(socket.getOutputStream());
        Scanner in = new Scanner(socket.getInputStream());
        ) {

      int sum = 0;

      while (true) {
        String str = in.nextLine();
        if (str.equals(""))
          break;
        sum += Integer.parseInt(str);
      }

      out.println("결과 = " + sum);
    }
  }
}






