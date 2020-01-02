// 클라이언트 만들기 
package com.eomcs.net.ex02;

import java.net.Socket;

// 클라이언트(client)?
// => 먼저 연결을 요청하는 쪽을 가리킨다.
public class Client1 {
  public static void main(String[] args) throws Exception {
    System.out.println("서버에 연결을 요청 중...");
    //1) 서버에 연결을 요청한다. 
    // => new Socket(서버주소, 포트번호)
    //    서버주소는 IP 또는 도메인명 모두 가능하다.
    Socket socket = new Socket("localhost", 8888);
    System.out.println("서버에 연결됨!");

    // 자원해제 
    socket.close();
  }
}








