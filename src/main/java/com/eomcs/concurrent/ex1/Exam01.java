// 멀티 스레드 적용 전
package com.eomcs.concurrent.ex1;

public class Exam01 {

  public static void main(String[] args) {
    // 코드는 위에서 아래로 순서대로 실행한다.
    for (int i = 0; i < 1000; i++) {
      System.out.println("==> " + i );
    }

    for (int i = 0; i < 1000; i++) {
      System.out.println(">>> " + i );
    }
  }
}

// 자바는 main() 메서드를 실행하는 한 개의 "실행 흐름"이 있다.
// 실행 흐름에 따라 순서대로 코드가 실행된다.










