// character stream - 문자 단위로 출력하기
package com.eomcs.io.ex03;

import java.io.FileWriter;

public class Exam0110 {
  public static void main(String[] args) throws Exception {
    // 1) 문자 단위로 출력할 도구 준비
    FileWriter out = new FileWriter("temp/test2.txt");

    // 2) 문자 출력하기
    // => 자바는 문자 데이터를 다룰 때 UCS2(2바이트) 유니코드를 사용한다.
    // => 그래서 출력할 때 UCS2 유니코드값을
    // JVM에 설정된 기본 문자코드표의 값으로 변환하여 출력한다.
    // => JVM을 실행할 때 출력 데이터의 문자 코드표를 지정하지 않으면
    // OS의 기본 문자코드표에 따라 변환한다.
    // 예) Windows OS(MS949), Unix(UTF-8)
    // => JVM을 실행할 때 출력 데이터의 문자 코드표를 지정하는 방법
    // > java -Dfile.encoding=문자코드표 -cp 클래스경로 클래스명
    //
    // [이클립스에서 실행]
    // - 출력할 때 UCS2를 UTF-8 코드로 변환하여 파일에 쓴다.
    //
    // [콘솔창에서 실행]
    // - OS가 기본으로 사용하는 문자 코드로 변환하여 파일에 쓴다.
    // - windows : MS949
    // - linux/unix/macOS : UTF-8
    // - 만약 JVM을 실행할 때 '-Dfile.encoding=문자코드표' 옵션을 지정했다면,
    // - 해당 옵션에 지정된 문자 코드로 변환하여 파일에 쓴다.
    //
    // => 이 예제를 이클립스에서 실행한다면,
    // 앞의 2바이트는 버리고,
    // 뒤의 2바이트(UCS2)를 UTF-8 코드표에 따라
    // 1 ~ 4 바이트 값으로 변환하여 파일에 쓴다.
    //
    out.write(0x7a6bac00);
    // - 앞의 2바이트(7a6b)는 버린다.
    // - 뒤의 2바이트(ac00)은 UTF-8(eab080) 코드 값으로 변환되어 파일에 출력된다.

    // UCS2에서 영어 A는 0041이다.
    // 출력하면, UTF-8 코드 값(41)이 파일에 출력된다.
    out.write(0x7a5f0041);
    // - 앞의 2바이트(7a5f)는 버린다.
    // - 뒤의 2바이트는 UTF-8(41) 코드 값으로 변환되어 파일에 출력된다.

    out.close();

    System.out.println("출력 완료!");

  }
}
