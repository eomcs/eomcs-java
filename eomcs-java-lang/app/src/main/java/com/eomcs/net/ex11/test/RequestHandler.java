package com.eomcs.net.ex11.test;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class RequestHandler extends Thread {

  Socket socket;
  String logo;

  public RequestHandler(Socket socket, String logo) {
    this.socket = socket;
    this.logo = logo;
  }

  @Override
  public void run() {
    try (
        Socket socket2 = socket; // close() 자동 호출하기 위해
        Scanner in = new Scanner(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream());
        ) {

      out.print(logo);

      out.println("계산식을 입력하세요!");
      out.println("예) 23 + 7 ");
      out.println();

      String str = in.nextLine();
      out.println(str);
      out.flush();

    } catch (Exception e) {
      System.out.println("클라이언트 요청 처리 중 오류 발생!");
    }
  }
}
