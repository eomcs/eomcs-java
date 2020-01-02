// Character Stream - 문자 배열 출력하기 
package com.eomcs.io.ex03;

import java.io.FileWriter;

public class Exam0210 {

  public static void main(String[] args) throws Exception {
    FileWriter out = new FileWriter("temp/test2.txt");

    char[] chars = new char[] {'A','B','C','가','각','간','똘','똥'}; 

    out.write(chars); // 문자 배열 전체를 출력한다.
    // 당연히 UTF-16을 JVM 기본 문자 코드표에 따라 변환하여 출력한다.
    // JVM이 입출력 문자 코드표로 UTF-8을 사용한다면
    // 영어는 1바이트로 변환되어 출력될 것이고,
    // 한글은 3바이트로 변환되어 출력될 것이다.

    out.close();

    System.out.println("데이터 출력 완료!");

  }

}
