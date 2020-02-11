// URL(Uniform Resource Locator) - URL을 다루는 클래스
package com.eomcs.net.ex07;

import java.net.URL;

public class Exam01 {

  public static void main(String[] args) throws Exception {
    // 웹 상에서 자원의 위치를 표현하는 방법
    // => [프로토콜]://서버주소:포트번호/자원의경로
    // - 프로토콜: http, https, ftp 등
    // - 서버주소: IP 주소(192.168.0.1), 도메인명(www.bitcamp.co.kr)
    // - 포트번호: 80(생략할 수 있다), 8080(프록시 서버) 등
    // - 자원의경로: /index.html, /board/list.jsp 등
    //
    URL url = new URL("https://bitcamp.co.kr:80/index.php?main_page=home");

    // URL 분석
    System.out.printf("프로토콜: %s\n", url.getProtocol());
    System.out.printf("서버주소: %s\n", url.getHost());
    System.out.printf("포트번호: %d\n", url.getPort());
    System.out.printf("자원경로: %s\n", url.getPath());
  }

}


