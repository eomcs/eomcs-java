// 스레드 그룹에 소속된 하위 스레드 그룹들
package ch24.b;

public class Test04 {

  public static void main(String[] args) {
    
    // 스레드 그룹에 소속된 하위 스레드 그룹 목록을 알아내기
    //
    Thread currThread = Thread.currentThread();
    ThreadGroup group = currThread.getThreadGroup();
    System.out.println("main 스레드 그룹에 소속된 하위 스레드 그룹들:");
    
    System.out.println(group.getName() + "(TG)");
    
    // enumerate(스레드 그룹를 담을 배열, 하위 스레드 그룹에 소속된 것도 포함할 것인지 여부)
    ThreadGroup[] groupList = new ThreadGroup[10];
    int size = 0;
    size = group.enumerate(groupList, false);
    
    for (int i = 0; i < size; i++) {
      System.out.println("  => " + groupList[i].getName() + "(TG)");
    }
    
  }

}

// JVM의 스레드 계층도:
// main(TG)
//   => main(T)











