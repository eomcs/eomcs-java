// Byte Stream - 텍스트 데이터 읽기 II
package com.eomcs.io.ex02;

import java.io.FileInputStream;

public class Exam0524 {

  public static void main(String[] args) throws Exception {
    FileInputStream in = new FileInputStream("sample/utf16be.txt");

    // 파일의 데이터를 한 번에 읽어보자.
    byte[] buf = new byte[1000];
    int count = in.read(buf);

    in.close();

    // 읽은 바이트를 정상적인 UCS2로 변환하여 String 객체를 생성해보자.
    // - 예) 
    //   utf16be.txt => 00 41 00 42 ac 00 ac 01
    //   UCS2        => 0041 0042 ac00 ac01  <== 한글이 UCS2로 정상적으로 변환되었다.
    String str = new String(buf, 0, count, "UTF-16"); // UTF-16 == UTF-16BE
    System.out.println(str);
  }

}
