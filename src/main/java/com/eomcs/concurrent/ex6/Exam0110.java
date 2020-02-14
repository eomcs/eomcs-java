// 스레드 재사용 - 1단계) 재 사용전
package com.eomcs.concurrent.ex6;

import java.util.Scanner;

public class Exam0110 {

  public static void main(String[] args) {

    class MyThread extends Thread {
      int count;

      public void setCount(int count) {
        this.count = count;
      }

      @Override
      public void run() {
        try {
          for (int i = count; i > 0; i--) {
            System.out.println("==> " + i);
            Thread.sleep(1000);
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

    MyThread t = new MyThread();

    Scanner keyScan = new Scanner(System.in);

    while (true) {
      System.out.print("카운트? ");
      String str = keyScan.nextLine();
      if (str.equals("quit")) {
        break;
      }

      int count = Integer.parseInt(str);
      t.setCount(count);
      t.start();
      // 만약 Dead 상태의 스레드를 다시 시작하려 하면 예외가 발생한다.
      // run() 메서드 호출이 끝나, Dead 상태가 된 스레드는
      // 다시 시작시킬 수 없다!
      // 주의!
      // => 이미 실행 중인 스레드 객체에 대해 start()를 또 호출하면 예외가 발생한다.
    }

    System.out.println("main 스레드 종료!");
  }
}

