// "system" 스레드 그룹의 자식 그룹들
package com.eomcs.concurrent.ex2;

public class Exam06 {

  public static void main(String[] args) {
    Thread main = Thread.currentThread();
    ThreadGroup mainGroup = main.getThreadGroup();
    ThreadGroup systemGroup = mainGroup.getParent();

    ThreadGroup[] groups = new ThreadGroup[100];
    int count = systemGroup.enumerate(groups, false);

    System.out.println("system 스레드 그룹의 자식 그룹들:");
    for (int i = 0; i < count; i++) { 
      System.out.println("   =>" + groups[i].getName());
    }

    // "system" 스레드 그룹: 
    //      => 다른 하위 그룹은 없다!
    //      =>"main" 스레드 그룹:
    //          =>"main" 스레드
    //          =>다른 하위 그룹은 없다!
  }

}
