//# 주석 - Javadoc 주석
//

package com.eomcs.basic.ch02.test;

//1) 클래스에 대한 간단한 설명을 Javadoc 주석으로 작성하라.

public class Exam11 {
  
  //2) 필드에 대한 간단한 설명을 Javadoc 주석으로 작성하라.

  static String message = "Hello, world!";
  
  //3) 메서드에 대한 간단한 설명을 Javadoc 주석으로 작성하라.

  public static void main(String[] args) {
    System.out.println(message);
  }
}

//4) javadoc 도구를 사용하여 Javadoc 문서를 생성하라.
//- com.eomcs.basic.ch02.test 패키지에 있는 자바 소스 파일에 대해서 문서를 생성한다.
//- eomcs-java-basic/javadoc 디렉토리에 javadoc 문서를 생성한다.
//