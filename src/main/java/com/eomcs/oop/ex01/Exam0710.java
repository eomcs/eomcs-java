package com.eomcs.oop.ex01;

// # import : 사용 전
//
public class Exam0710 {
  public static void main(String[] args) {

    // 다른 패키지의 클래스 사용
    // => 패키지 이름을 항상 붙여야 한다.
    //    그래야만 컴파일러가 해당 클래스를 찾을 수 있다.
    com.eomcs.oop.ex01.sub.Y2 obj;
    obj = new com.eomcs.oop.ex01.sub.Y2();

  }
}
