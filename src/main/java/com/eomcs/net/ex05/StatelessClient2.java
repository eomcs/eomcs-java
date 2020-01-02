// Stateless 클라이언트 - 클라이언트 식별 번호 적용 
package com.eomcs.net.ex05;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class StatelessClient2 {
  public static void main(String[] args) throws Exception {
    int clientId = 0; // 서버로부터 클라이언트 ID를 발급 받기 전에는 0으로 설정한다.

    // 합계 계산을 요청하는 클라이언트를 만들어보자!
    try (Scanner keyScan = new Scanner(System.in)) {
      while (true) {
        System.out.print("값? ");
        String value = keyScan.nextLine(); // 키보드에서 값을 입력 받아

        try (
            Socket socket = new Socket("localhost", 8888);
            PrintStream out = new PrintStream(socket.getOutputStream());
            Scanner in = new Scanner(socket.getInputStream())) {

          out.println(value); // 서버에 값을 보낸다. 
          out.printf("%d\n", clientId);

          if (value.equals("")) { // 만약 값을 입력하지 않았으면 
            System.out.println(in.nextLine()); // 서버가 보낸 결과를 출력한다.
            break;
          }

          // 서버가 보낸 값은 항상 클라이언트 식별번호(ID)이기 때문에
          // 받은 값을 보관해야 한다.
          clientId = Integer.parseInt(in.nextLine());
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






