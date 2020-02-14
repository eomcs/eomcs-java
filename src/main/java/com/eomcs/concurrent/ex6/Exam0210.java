// 멀티 스레드 재사용
package com.eomcs.concurrent.ex6;

import java.util.ArrayList;
import java.util.Scanner;

public class Exam0210 {

  public static void main(String[] args) {

    class MyThread extends Thread {
      int count;

      public MyThread(String name) {
        super(name);
      }

      public void setCount(int count) {
        this.count = count;
        synchronized (this) {
          notify();
        }
      }

      @Override
      public void run() {
        synchronized (this) {
          try {
            while (true) {
              wait();
              for (int i = count; i > 0; i--) {
                System.out.printf("[%s] %d\n", getName(), i);
                Thread.sleep(1000);
              }
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    }

    class MyThreadPool {
      ArrayList<MyThread> list = new ArrayList<>();

      public MyThreadPool() {
        MyThread t1 = new MyThread("=>");
        t1.start();
        list.add(t1);

        MyThread t2 = new MyThread("------");
        t2.start();
        list.add(t2);

        MyThread t3 = new MyThread("$$$");
        t3.start();
        list.add(t3);
      }

      // 스레드 풀에서 한 개의 스레드를 꺼낸다.
      public MyThread get() {
        if (list.size() > 0) {
          return list.remove(0);
        }
        return null;
      }

      // 스레드를 다 쓴 후에는 다시 스레드 풀에 돌려준다.
      public void add(MyThread t) {
        list.add(t);
      }
    }

    // 스레드풀 준비!
    MyThreadPool threadPool = new MyThreadPool();

    Scanner keyScan = new Scanner(System.in);

    while (true) {
      System.out.print("카운트? ");
      String str = keyScan.nextLine();
      if (str.equals("quit")) {
        break;
      }

      int count = Integer.parseInt(str);

      // 스레드풀에서 스레드를 한 개 꺼낸다.
      MyThread t = threadPool.get();
      if (t == null) {
        System.out.println("남는 스레드가 없습니다!");
        continue;
      }

      // 스레드풀에서 스레드를 받았다면 카운트를 시작시킨다.
      t.setCount(count);
    }

    System.out.println("main 스레드 종료!");
    keyScan.close();
  }
}

