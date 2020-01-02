// 응용 - 에코 클라이언트 만들기
package com.eomcs.net.ex03;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
  public static void main(String[] args) throws Exception {
    Socket socket = new Socket("localhost", 8888);

    Scanner in = new Scanner(socket.getInputStream());
    PrintStream out = new PrintStream(socket.getOutputStream());

    // 데이터를 읽고 출력할 때는 반드시 서버와의 통신 규칙을 따라야 한다.
    // 클라이언트가 먼저 데이터를 보내기로 했으면 먼저 데이터를 보내야 한다.
    out.println(args[0]);

    // 서버가 보낸 데이터를 읽고 화면에 출력한다.
    String str = in.nextLine();
    System.out.println(str);


    in.close();
    out.close();
    socket.close();
  }
}





