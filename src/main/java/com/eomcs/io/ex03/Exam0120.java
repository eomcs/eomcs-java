// character stream - 문자 단위로 읽기 
package com.eomcs.io.ex03;

import java.io.FileReader;

public class Exam0120 {

  public static void main(String[] args) throws Exception {
    // 1) 파일의 데이터를 읽을 객체를 준비한다. 
    FileReader in = new FileReader("temp/test2.txt");

    // 2) JVM에 설정된 문자코드표에 따라 바이트를 읽어서 UTF-16으로 바꾼 후에 리턴한다.
    // => 리턴 값은 2바이트 UTF-16의 코드 값이다.
    // => JVM에 설정된 문자코드표가 UTF-8이면 1 ~ 4 바이트까지 
    //    문자에 따라 가변적으로 읽어들이다.
    //    즉 영어 문자는 1바이트를 읽어 2바이트 UTF-16 코드 값으로 바꿀 것이다.
    //    한글 문자는 3바이트를 읽어 2바이트 UTF-16 코드 값으로 바꿀 것이다.
    //    문자에 따라 읽는 바이트의 개수가 다르다는 것이다.
    // => 이것이 FileInputStream의 read() 메서드와 다른 점이다.
    //    FileInputStream의 read()는 무조건 1바이트를 읽는다.
    //    그리고 값을 변환하지 않는다.
    // => FileReader의 read()는 문자에 따라 1 바이트에서 4 바이트까지 
    //    읽는 바이트 수가 다른다. 
    //    리턴 값도 읽을 값을 그대로 리턴하는 것이 아니라 UTF-16 코드 값으로 변경하여 
    //    리턴한다. 
    //    왜? JVM에서 문자를 UTF-16 코드 값으로 다루기 때문이다.
    // => 그래서 이미지 파일이나 동영상 파일과 같이 바이너리 데이터는 
    ///   FileReader를 사용하여 읽어서는 안된다.
    //    왜? 문자라고 간주하고 값을 변경하기 때문이다.
    int ch = in.read(); 

    // 3) 읽기 도구를 닫는다.
    in.close();

    System.out.printf("%x\n", ch);
  }
}






