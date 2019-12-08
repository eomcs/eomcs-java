// URL 다루기 - OS의 파일시스템 가리키기
package ch23.h;

import java.net.URL;

public class Test03 {

  public static void main(String[] args) throws Exception {
    // 파일 시스템
    // => [프로토콜]://서버주소:포트번호/자원의경로
    //    예) file:///c:/Users/bitcamp/a.html
    
    // URL 형식으로 파일시스템의 자원을 가리키기(for Windows)
    URL url = new URL("file:///c:/Users/bitcamp/a.html");
    
    System.out.printf("프로토콜: %s\n", url.getProtocol());
    System.out.printf("서버주소: %s\n", url.getHost());
    System.out.printf("포트번호: %d\n", url.getPort()); 
    System.out.printf("자원경로: %s\n", url.getPath());

    System.out.println("------------------------------");
    
    // URL 형식으로 파일시스템의 자원을 가리키기(for Unix)
    url = new URL("file:///home/bitcamp/a.html");
    
    System.out.printf("프로토콜: %s\n", url.getProtocol());
    System.out.printf("서버주소: %s\n", url.getHost());
    System.out.printf("포트번호: %d\n", url.getPort()); 
    System.out.printf("자원경로: %s\n", url.getPath());
  }

}






