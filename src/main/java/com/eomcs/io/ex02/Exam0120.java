// Byte Stream - 바이트 단위로 읽기 
package com.eomcs.io.ex02;

import java.io.FileInputStream;

public class Exam0120 {

  public static void main(String[] args) throws Exception {
    // 1) 파일의 데이터를 읽을 객체를 준비한다. 
    FileInputStream in = new FileInputStream("temp/test1.data");

    // 2) 1바이트를 읽는다.
    // => read() 메서드의 리턴 타입이 int 라 하더라도 1바이트를 읽어 리턴한다.
    int b = in.read(); // 읽은 값은 0x4d 이다.

    // 3) 읽기 도구를 닫는다.
    in.close();

    System.out.printf("%x\n", b);

  }

}
