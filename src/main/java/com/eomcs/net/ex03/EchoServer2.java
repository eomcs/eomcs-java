// 응용 - 에코 서버 만들기 + 여러 클라이언트의 요청을 처리하기
package com.eomcs.net.ex03;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer2 {
  public static void main(String[] args) throws Exception {
    System.out.println("서버 실행 중...");

    ServerSocket ss = new ServerSocket(8888);

    while (true) {
      Socket socket = ss.accept();
      Scanner in = new Scanner(socket.getInputStream());
      PrintStream out = new PrintStream(socket.getOutputStream());

      String str = in.nextLine();
      out.println(str);

      in.close();
      out.close();
      socket.close();
    }

    //ss.close();
  }
}





