// URL(Uniform Resource Locator) - 포트번호 생략
package com.eomcs.net.ex07;

import java.net.URL;

public class Exam02 {

  public static void main(String[] args) throws Exception {
    // 웹 상에서 자원의 위치를 표현하는 방법
    URL url = new URL("https://bitcamp.co.kr/index.php");

    // URL 분석
    System.out.printf("프로토콜: %s\n", url.getProtocol());
    System.out.printf("서버주소: %s\n", url.getHost());

    System.out.printf("포트번호: %d\n", url.getPort());
    // 웹브라우저에서는 포트번호를 생략하면 80번으로 간주한다.
    // 다만 getPort()의 리턴 값은 -1 이다.

    System.out.printf("자원경로: %s\n", url.getPath());
    System.out.printf("QueryString: %s\n", url.getQuery()); // 없으면 null 리턴

  }

}


