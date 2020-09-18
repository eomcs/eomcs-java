// Byte Stream - 텍스트 데이터 읽기
package com.eomcs.io.ex02;

import java.io.FileInputStream;

public class Exam0520 {

  public static void main(String[] args) throws Exception {
    StringBuffer strbuf = new StringBuffer();

    FileInputStream in = new FileInputStream("sample/ms949.txt");

    // 'A' 문자 읽기
    int b = in.read(); // 1byte 읽기
    strbuf.append((char)b); // 00 00 00 41(ASCII) ==> 00 41(UCS2=UTF-16 BE)

    // 'B' 문자 읽기
    b = in.read(); // 1byte 읽기
    strbuf.append((char)b); // 00 00 00 42(ASCII) ==> 00 42(UCS2=UTF-16 BE)

    // '가' 문자 읽기
    b = in.read() << 8 | in.read(); // 2byte 읽기
    // 00 00 b0 00
    // 00 00 00 a1
    // -----------
    // 00 00 b0 a1
    strbuf.append((char)b); // 00 00 b0 a1(MS949) ==> b0 a1(?) UCS2에서 한글 '가'는 ac00 이다. 
    // 위와 같이 MS949로  인코딩된 파일에서 한글을 읽었을 때 
    // 그대로  StringBuffer에 저장하면 안된다.
    // JVM에서 사용하는 UCS2 로 인코딩을 변환해서 저장해야 한다.
    // 어떻게 변환하는가?
    // - 다음 예제를 확인하라!

    // '각' 문자 읽기
    b = in.read() << 8 | in.read(); // 2byte 읽기
    // 00 00 b0 00
    // 00 00 00 a2
    // -----------
    // 00 00 b0 a2
    strbuf.append((char)b); // 00 00 b0 a2(MS949) ==> b0 a2(?) UCS2에서 한글 '각'는 ac02 이다.

    in.close();

    System.out.println(strbuf.toString());
  }

}
