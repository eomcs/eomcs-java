// Byte Stream - 읽은 데이터를 바이트 배열의 특정 위치에 저장하기
package com.eomcs.io.ex02;

import java.io.FileInputStream;

public class Exam0320 {

  public static void main(String[] args) throws Exception {
    FileInputStream in = new FileInputStream("temp/test1.data");

    byte[] buf = new byte[100];

    // read(버퍼의주소, 저장할위치, 읽을바이트개수)
    // => 리턴 값은 실제 읽은 바이트의 개수이다.
    int count = in.read(buf, 10, 40); // 40바이트를 읽어 10번 방부터 저장한다.

    in.close();

    System.out.printf("%d\n", count);

    for (int i = 0; i < 20; i++)
      System.out.printf("%x ", buf[i]);

    System.out.println();

  }

}


