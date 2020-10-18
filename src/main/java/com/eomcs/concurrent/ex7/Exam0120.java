// Executors 태스크 프레임워크 - 스레드풀 종료하기
package com.eomcs.concurrent.ex7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exam0120 {

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    executorService.execute(() -> System.out.printf("%s - Hello!\n",
        Thread.currentThread().getName()));

    // 스레드풀에 있는 모든 스레드들이 요청한 작업을 끝내면 종료하게 한다.
    // 그리고 새 작업 요청은 받지 않는다.
    executorService.shutdown();

    System.out.println("main() 종료!");
  }
}

