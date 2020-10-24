package com.eomcs.lang.ex04;

//# 로컬 변수와 블록 II
//
public class Exam0821 {
  public static void main(String[] args) {
    
    int a = 100;
    
    for (int i = 0; i < 10; i++) {
      // 바깥 블록에 선언된 변수와 같은 이름으로 변수를 만들 수 없다.
      //int a; // 컴파일 오류!
      int b; // OK!
    }
    
  }
}

