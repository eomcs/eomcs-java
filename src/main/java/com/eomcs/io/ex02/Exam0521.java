// Byte Stream - 텍스트 데이터 읽기 II
package com.eomcs.io.ex02;

import java.io.FileInputStream;

public class Exam0521 {

  public static void main(String[] args) throws Exception {
    FileInputStream in = new FileInputStream("sample/ms949.txt");

    // 파일의 데이터를 한 번에 읽어보자.
    byte[] buf = new byte[1000];
    int count = in.read(buf);

    in.close();

    // 읽은 바이트를 String 객체로 만들어보자.
    String str = new String(buf, 0, count);
    System.out.println(str);

    // 한글이 깨진 이유?
    // - 파일에서 읽어 들인 바이트는 MS949로 인코딩 된 텍스트 파일이다.
    // - String 객체를 만들 때 바이트 배열의 인코딩을 알려주지 않으면 
    //   문자가 ISO-8859-1(영어)로 인코딩 되었다고  가정하고
    //   각 바이트 앞에 00 1바이트를 붙여 UCS2 인코딩으로 변환한다.
    // - 예)
    //   ms949.txt => 41 42 b0 a1 b0 a2
    //   UCS2      => 0041 0042 00b0 00a1 00b0 00a2   <== 한글이 정상적인 UCS2로 변환되지 않았다.
    // - 영어인 경우 앞에 00 1바이트를 붙이면 정상적인 UCS2 문자가 되지만,
    //   한글인 경우는 별도의 규칙에 따라 변환해야 한다. 
    //   그렇게 하지 않았기 때문에 한글만 깨진 것이다.


  }

}
