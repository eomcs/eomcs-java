// 클라이언트와 입출력 테스트
package com.eomcs.net.ex03;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server011x {
  public static void main(String[] args) throws Exception {
    Scanner keyScan = new Scanner(System.in);
    System.out.println("서버 실행!");

    keyScan.nextLine();
    ServerSocket ss = new ServerSocket(8888);
    System.out.println("클라이언트 연결을 기다리는 중...");

    keyScan.nextLine();
    Socket socket = ss.accept();
    System.out.println("대기 중인 클라이언트 중 한 개의 클라이언트에 대해 연결 승인!");

    PrintStream out = new PrintStream(socket.getOutputStream());
    Scanner in = new Scanner(socket.getInputStream());
    System.out.println("입출력 스트림 준비!");

    keyScan.nextLine();

    System.out.println("클라이언트 데이터 수신 중...");
    System.out.println(in.nextLine());
    System.out.println("클라이언트 데이터 수신 완료!");

    System.out.println("클라이언트에게 응답 보냄!");
    out.println("OK!");

    // 자원 해제
    socket.close();
    System.out.println("클라이언트와의 연결 해제!");

    ss.close();
    System.out.println("서버 종료!");
  }
}

