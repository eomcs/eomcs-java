// Byte Stream - 텍스트 데이터 읽기
package com.eomcs.io.ex02;

import java.io.FileInputStream;

public class Exam0520 {

  public static void main(String[] args) throws Exception {
    StringBuffer strbuf = new StringBuffer();

    FileInputStream in = new FileInputStream("sample/ms949.txt");

    // 'A' 문자 읽기
    int b = in.read(); // 1byte 읽기
    System.out.printf("%x\n", b);

    // 'B' 문자 읽기
    b = in.read(); // 1byte 읽기
    System.out.printf("%x\n", b);

    // MS949로 문자를 저장하면, 한글은 2바이트로 저장된다.
    // 따라서 한글을 읽을 때는 2바이트를 묶어서 읽어야 한다.
    // '가' 문자 읽기
    b = in.read() << 8 | in.read(); // 2byte 읽기
    System.out.printf("%x\n", b);

    // '각' 문자 읽기
    b = in.read() << 8 | in.read(); // 2byte 읽기
    System.out.printf("%x\n", b);

    in.close();

    System.out.println(strbuf.toString());
  }

}
