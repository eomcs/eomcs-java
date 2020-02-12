// 스레드의 생명주기(lifecycle) - 죽은 스레드는 다시 살릴 수 없다.
package com.eomcs.concurrent.ex4;

public class Exam0111 {
  public static void main(String[] args) {
    Thread t = new Thread(() -> { // Runnable 구현체를 정의하고 생성한다.
      for (int i = 0; i < 1000; i++) {
        System.out.println("===> " + i);
      }
    });
    t.start();

    // 같은 스레드 객체를 또 실행할 수 없다.
    // t.start(); // 예외 발생!

    System.out.println("main() 종료!");
  }

}
