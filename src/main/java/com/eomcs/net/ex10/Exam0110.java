// Base64 인코딩
package com.eomcs.net.ex10;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Exam0110 {

  public static void main(String[] args) throws Exception {
    String str = "ABC012가각간";
    
    byte[] bytes = str.getBytes("UTF-8");
    for (byte b : bytes) {
      System.out.printf("%x ", b);
    }
    System.out.println();
    
    // Base64 인코딩
    // => 바이너리 데이터를 문자화시킨다.
    // => 바이너리 값을 6비트식 잘라서(2의 6승) 
    //    64진수으로 만든 후 Base64 표에 정의된 대로
    //    해당 값을 문자로 변환한다.
    // => 보통 바이너리 데이터를 텍스트로 전송하고 싶을 때 많이 사용한다.
    // => "ABC012가간" 문자열
    //    414243303132EAB080EAB081(UTF-8 코드)
    //    4142 ==> 0100000101000010... (2진수)
    //    010000 010100 0010... (6비트씩 자른 것)
    //    6비트로 자른 것을 다시 10진수로 표현하면 ==> 16 20 ...
    //    16을 Base64 코드표에 따라 문자로 바꾸면 ==> Q
    //    20을 Base64 코드표에 따라 문자로 바꾸면 ==> U
    //    ...
    //    이런 식으로 문자열을 Base64로 바꾸면 결과는 다음과 같다.
    //    QUJDMDEy6rCA6rCB6rCE
    Encoder encoder = Base64.getEncoder();
    byte[] base64Bytes = encoder.encode(bytes);
    for (byte b : base64Bytes) {
      System.out.printf("%x ", b);
    }
    System.out.println();
    
    System.out.println(new String(base64Bytes));
    
    // Base64 디코딩
    // => Base64 코드를 원래의 바이너리 값으로 변환한다.
    //
    Decoder decoder = Base64.getDecoder();
    byte[] bytes2 = decoder.decode(base64Bytes);
    
    System.out.println(new String(bytes2, "UTF-8"));
    
    
  }

}
