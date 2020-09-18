// 텍스트 파일 읽기 - 바이트 입력 스트림을 사용하여 문자 데이터를 읽을 때 
package com.eomcs.io.ex03;

import java.io.FileWriter;

public class Exam0110 {
  public static void main(String[] args) throws Exception {
    FileWriter out = new FileWriter("sample/ms949.txt");

    out.write(0x7a6bac00); 
    // - 앞의 2바이트(7a6b)는 버린다.
    // - 뒤의 2바이트(ac00)은 UTF-8(eab080) 코드 값으로 변환되어 파일에 출력된다.

    // UCS2에서 영어 A는 0041이다.
    // 출력하면, UTF-8 코드 값(41)이 파일에 출력된다.
    out.write(0x7a5f0041);  // 
    // - 앞의 2바이트(7a5f)는 버린다.
    // - 뒤의 2바이트는 UTF-8(41) 코드 값으로 변환되어 파일에 출력된다.

    out.close();

    System.out.println("출력 완료!");

  }
}
