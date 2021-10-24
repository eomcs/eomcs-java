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
        System.out.printf("[%d] %s 스레드 실행 중...\n",
            this.millisec, Thread.currentThread().getName());

        Thread.sleep(millisec);
        //        for (long i = 0; i < 10000_0000; i++) {
        //          double r = Math.tan(3456.77889) / Math.random();
        //
        //        }

        System.out.printf("[%d] %s 스레드 종료!\n",
            this.millisec, Thread.currentThread().getName());

      } catch (Exception e) {
        System.out.printf("[%d] %s 스레드 실행 중 오류 발생!\n", 
            this.millisec, Thread.currentThread().getName());
      }
    }
  }
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(3);

    executorService.execute(new MyRunnable(1000));
    executorService.execute(new MyRunnable(2000));
    executorService.execute(new MyRunnable(3000));
    executorService.execute(new MyRunnable(7000));
    executorService.execute(new MyRunnable(8000));
    executorService.execute(new MyRunnable(9000));

    // 가능한 현재 수행 중인 작업들을 모두 멈추도록 지시한다.
    // => shutdown()과 차이점:
    //    - 만약 작업을 실행 중인 스레드가 잠시 Not Runnable 상태에 놓인다면,
    //      바로 스레드를 멈출 기회라고 보고 스레드 종료를 시도할 것이다.
    //    - 이런 이유로 shutdown() 보다는 스레드 종료를 좀 더 강제하는 효과가 있다.
    // => 작업 중인 스레드가 Not Runnable 상태가 아니라면 작업이 끝날 때까지 기다린다.
    // => 다만 작업을 마치고 대기 중인 스레드는 즉시 취소한다.
    // => 그리고 취소한 대기 작업 목록을 리턴해준다.
    // 
    List<Runnable> tasks = executorService.shutdownNow();
    System.out.println("실행 취소된 작업들:");
    System.out.println("--------------------------------");
    for (Runnable task : tasks) {
      System.out.println(((MyRunnable) task).millisec);
    }
    System.out.println("--------------------------------");

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

