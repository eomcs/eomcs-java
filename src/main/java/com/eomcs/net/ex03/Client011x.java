// 서버와 입출력 테스트
package com.eomcs.net.ex03;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client011x {
  public static void main(String[] args) throws Exception {
    System.out.println("클라이언트 실행!");

    Socket socket = new Socket("localhost", 8888);
    System.out.println("서버에 연결됨!");

    // 서버랑 데이터를 주고 받을 수 있도록 입출력 스트림 객체를 준비한다.
    // => 출력 스트림 객체를 준비하기
    PrintStream out = new PrintStream(socket.getOutputStream());
    Scanner in = new Scanner(socket.getInputStream());
    System.out.println("입출력 스트림 준비!");

    System.out.println("서버에 데이터 전송중...");
    out.println("Hello");
    System.out.println("서버에 데이터 전송 완료!");

    System.out.println("서버로부터 데이터 수신 중...");
    System.out.println(in.nextLine());
    System.out.println("서버로부터 데이터 수신 완료!");

    socket.close();
  }
}


