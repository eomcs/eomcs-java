// 상대편으로부터 연결 요청 받기 - 서버(server)
package com.eomcs.net.ex01;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Receiver {

  public static void main(String[] args) throws Exception {
    System.out.println("서버 실행!");

    // 1) 다른 컴퓨터의 연결 요청을 기다린다.
    // - new ServerSocket(포트번호)
    // - 포트번호: 
    //   - 1024 ~ 49151 사이의 값 사용한다.
    //   - 같은 컴퓨터에서 이미 사용중인 포트 번호는 지정할 수 없다.
    //   - 예) Oracle DBMS(1521), MySQL DBMS(3306) 등
    ServerSocket serverSocket = new ServerSocket(8888);
    System.out.println("ServerSocket 생성!");

    // 2) 연결하기 위해 대기하고 있는 클라이언트 중에서 한 개를 연결 허락한다.
    // - 클라이언트가 서버에 접속을 요청하면 그 정보를 "대기열"이라고 불리는 목록으로 관리한다.
    // - accept()를 호출하면 대기열에서 순서대로 꺼내 해당 클라이언트와 연결된 소켓을 만든다.
    Socket socket = serverSocket.accept();
    System.out.println("클라이언트와 연결된 Socket 생성!");

    // 3) 소켓 객체를 통해 읽고 쓸 수 있도록 입출력 스트림을 얻는다.
    // - 연결된 클라이언트로 데이터를 보내고 받으려면 입출력 스트림을 꺼내야 한다.
    // - 소켓이 리턴해준 입출력 스트림에 적절한 데코레이터를 붙여서 사용한다.
    PrintStream out = new PrintStream(socket.getOutputStream());
    Scanner in = new Scanner(socket.getInputStream());
    System.out.println("데이터 송수신을 위한 입출력 스트림 준비!");

    // 4) 상대편이 보낸 문자열을 한 줄 읽는다.
    // => 상대편이 한 줄의 데이터를 보낼 때까지 리턴하지 않는다.
    // => 이런 메서드를 블로킹 메서드라 부른다.
    String str = in.nextLine();
    System.out.printf("상대편> %s\n", str);

    // 5) 상대편으로 문자열을 한 줄 보낸다.
    out.println("나는 엄진영입니다. 반갑습니다!");

    // 6) 항상 입출력 도구는 사용 후 닫아야 한다.
    in.close();
    out.close();

    // 7) 네트워크 연결도 닫는다.
    socket.close();
    serverSocket.close();

  }

}


