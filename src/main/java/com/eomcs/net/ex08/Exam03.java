// URL(Uniform Resource Locator) - 문서 내의 위치 지정
package com.eomcs.net.ex08;

import java.net.URL;

public class Exam03 {

  public static void main(String[] args) throws Exception {
    // 웹 상에서 자원의 위치를 표현하는 방법 
    URL url = new URL("http://www.bitcamp.co.kr/a/b/hello.html#footer");

    // URL 분석
    System.out.printf("프로토콜: %s\n", url.getProtocol());
    System.out.printf("서버주소: %s\n", url.getHost());
    System.out.printf("포트번호: %d\n", url.getPort());
    System.out.printf("자원경로: %s\n", url.getPath());
    System.out.printf("참조경로(내부위치): %s\n", url.getRef());

  }

}






