// 응용 - 에코 서버 만들기
package com.eomcs.net.ex13;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
  public static void main(String[] args) throws Exception {
    System.out.println("서버 실행 중...");

    ServerSocket ss = new ServerSocket(8888);

    // 대기열에서 기다리고 있는 클라이언트 중에서 제일 첫번째 클라이언트에 대해 연결을 승인!
    // => 그 클라이언트와 통신할 수 있는 소켓을 리턴한다.
    Socket socket = ss.accept();

    Scanner in = new Scanner(socket.getInputStream());
    PrintStream out = new PrintStream(socket.getOutputStream());

    // 데이터를 읽고 출력할 때는 반드시 클라이언트와의 통신 규칙을 따라야 한다.
    // 클라이언트가 먼저 데이터를 보냈으면 서버에서는 먼저 읽어야 한다.
    String str = in.nextLine();
    out.println(str);

    in.close();
    out.close();
    socket.close();
    ss.close();
  }
}





