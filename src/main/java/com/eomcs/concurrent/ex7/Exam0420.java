// Executors 태스크 프레임워크 - 스레드풀 종료 : shutdownNow()
package com.eomcs.concurrent.ex7;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exam0420 {

  static class MyRunnable implements Runnable {
    int millisec;

    public MyRunnable(int millisec) {
      this.millisec = millisec;
    }

    @Override
    public void run() {
      try {
        System.out.printf("%s 스레드 실행 중...\n",
            Thread.currentThread().getName());

        Thread.sleep(millisec);

        System.out.printf("%s 스레드 종료!\n",
            Thread.currentThread().getName());
      } catch (Exception e) {
        System.out.printf("%s 스레드 실행 중 오류 발생!\n", Thread.currentThread().getName());
      }
    }
  }
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(3);

    executorService.execute(new MyRunnable(6000));
    executorService.execute(new MyRunnable(2000));
    executorService.execute(new MyRunnable(4000));
    executorService.execute(new MyRunnable(5000));
    executorService.execute(new MyRunnable(6000));
    executorService.execute(new MyRunnable(7000));

    // 현재 수행 중인 작업들을 모두 멈추도록 지시한다.
    // => 대기 중인 작업들은 취소한다.
    // => 그리고 취소한 작업 목록을 리턴해준다.
    List<Runnable> tasks = executorService.shutdownNow();
    for (Runnable task : tasks) {
      System.out.println(((MyRunnable) task).millisec);
    }
    // 물론 새 작업 요청도 거절한다.
    // => 예외 발생!
    executorService.execute(new MyRunnable(4000));

    // shutdown() vs shutdownNow();
    // - shutdown()
    //   진행 중인 작업을 완료하고 대기 중인 작업도 완료한 다음 종료.
    // - shutdownNow()
    //   진행 중인 작업을 즉시 종료하고, 대기 중인 작업 목록은 리턴한다.
    System.out.println("main() 종료!");
  }
}

