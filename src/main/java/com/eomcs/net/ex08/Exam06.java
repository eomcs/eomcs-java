// URL(Uniform Resource Locator) - 유닉스 OS의 로컬 파일 경로
package com.eomcs.net.ex08;

import java.net.URL;

public class Exam06 {

  public static void main(String[] args) throws Exception {
    // 로컬 자원의 위치를 URL로 표현하는 방법 
    URL url = new URL("file:///home/hongkildong/test.txt");

    // URL 분석
    System.out.printf("프로토콜: %s\n", url.getProtocol());
    System.out.printf("서버주소: %s\n", url.getHost());
    System.out.printf("포트번호: %d\n", url.getPort());
    System.out.printf("자원경로: %s\n", url.getPath());

  }

}






