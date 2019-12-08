// 스레드 그룹
package ch24.b;

public class Test02 {

  public static void main(String[] args) {
    
    // 자바의 스레드는 특정 그룹에 소속된다.
    // "main" 스레드의 그룹은?
    //
    Thread currThread = Thread.currentThread();
    ThreadGroup group = currThread.getThreadGroup();
    
    System.out.printf("스레드 그룹명: %s\n", group.getName());
    
  }

}

// JVM의 스레드 계층도:
// main(TG)
//   => main(T)













