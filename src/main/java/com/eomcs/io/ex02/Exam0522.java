// Byte Stream - 텍스트 데이터 읽기 II
package com.eomcs.io.ex02;

import java.io.FileInputStream;

public class Exam0522 {

  public static void main(String[] args) throws Exception {
    FileInputStream in = new FileInputStream("sample/ms949.txt");

    // 파일의 데이터를 한 번에 읽어보자.
    byte[] buf = new byte[1000];
    int count = in.read(buf);

    in.close();

    // 읽은 바이트를 정상적인 UCS2로 변환하여 String 객체를 생성해보자.
    // - 바이트 배열에 들어 있는 문자의 코드가 어떤 규칙에 따라 인코딩 되었는지 알려주면 된다.
    // - ms949.txt 는 한글이 MS949 로 인코딩 되었기 때문에 
    //   다음과 같이 String 객체를 생성할 때 알려줘야 한다.
    // - 그러면 MS949 인코딩 데이터를 정상적으로 UCS2 인코딩 문자로 변환할 것이다.
    // - 예) 
    //   ms949.txt => 41 42 b0 a1 b0 a2
    //   UCS2      => 0041 0042 ac00 ac01  <== 한글이 UCS2로 정상적으로 변환되었다.
    String str = new String(buf, 0, count, "MS949");
    System.out.println(str);
  }

}
