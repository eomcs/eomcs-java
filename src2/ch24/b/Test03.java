// 스레드 그룹에 소속된 스레드들
package ch24.b;

public class Test03 {

  public static void main(String[] args) {
    
    // 스레드 그룹에 소속된 스레드 목록을 알아내기
    //
    Thread currThread = Thread.currentThread();
    ThreadGroup group = currThread.getThreadGroup();
    System.out.println("main 스레드 그룹에 소속된 스레드들:");

    System.out.println(group.getName() + "(TG)");
    
    // enumerate(스레드를 담을 배열, 하위 스레드 그룹에 소속된 것도 포함할 것인지 여부)
    Thread[] threadList = new Thread[10];
    int size = 0;
    size = group.enumerate(threadList, false);
    
    for (int i = 0; i < size; i++) {
      System.out.println("  => " + threadList[i].getName() + "(T)");
    }
    
  }

}

// JVM의 스레드 계층도:
// main(TG)
//   => main(T)
