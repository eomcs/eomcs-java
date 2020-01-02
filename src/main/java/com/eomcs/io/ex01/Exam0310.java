// 디렉토리 생성
package com.eomcs.io.ex01;

import java.io.File;

public class Exam0310 {

  public static void main(String[] args) throws Exception {
    // 1) 생성할 디렉토리 경로 설정
    File dir = new File("temp");
    if (dir.mkdir()) { // 디렉토리 생성
      System.out.println("temp 디렉토리를 생성하였습니다.");
    } else {
      System.out.println("temp 디렉토리를 생성할 수 없습니다.");
    }


  }

}
