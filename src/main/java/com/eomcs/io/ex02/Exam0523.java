// Byte Stream - 텍스트 데이터 읽기 II
package com.eomcs.io.ex02;

import java.io.FileInputStream;

public class Exam0523 {

  public static void main(String[] args) throws Exception {
    FileInputStream in = new FileInputStream("sample/utf8.txt");

    // 파일의 데이터를 한 번에 읽어보자.
    byte[] buf = new byte[1000];
    int count = in.read(buf);

    in.close();

    // 읽은 바이트를 정상적인 UCS2로 변환하여 String 객체를 생성해보자.
    // - 예) 
    //   utf8.txt  => 41 42 ea b0 80 ea b0 81
    //   UCS2      => 0041 0042 ac00 ac01  <== 한글이 UCS2로 정상적으로 변환되었다.
    String str = new String(buf, 0, count, "UTF-8");
    System.out.println(str);
  }

}
