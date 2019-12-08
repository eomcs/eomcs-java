// 스레드의 생명주기
package ch24.c;

public class Test03 {

  
  public static void main(String[] args) throws Exception {
    // 스레드의 생명주기
    // new Thread()    start()              sleep()/wait()
    //     준비 -------------------> Running ---------------> Not Runnable
    //                               ^  |    <---------------
    //                               |  |    timeout/notify()
    //                               X  |
    //                               |  |  run() 메서드 종료
    //                               |  V
    //                               Dead
    // Running 상태?
    // - CPU를 받아서 실행 중이거나 CPU를 받을 수 있는 상태
    //
    // Not Runnable 상태?
    // - CPU를 받지 않는 상태
    // 
    // run() 메서드 종료 후 다시 running 상태로 돌아갈 수 없다. 
    // => 새로 스레드를 만들어 실행하는 방법 밖에 없다!
    Thread t = new Thread() {
      @Override
      public void run() {
        for (int i = 0; i < 1000; i++) {
          System.out.printf("스레드 ===> %d\n", i);
        }
      }
    }; // 스레드 객체 생성 => 준비 상태
    t.start(); // => Running 상태
    
    new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 1000; i++) {
          System.out.printf("스레드2 >>>>>>>>>>>>>>>> %d\n", i);
        }
      }
    }).start(); // 스레드 객체 생성 => 준비 상태 => Running 상태 
    
    // main 스레드를 5초 동안 Not Runnable 상태에 둔다.
    // 즉 main 스레드를 실행하지 않는다.
    Thread.currentThread().sleep(5000);
    
    // 주의!
    // => dead 상태에서 다시 실행할 수 없다.
    t.start(); // 예외 발생!
    
    for (int i = 0; i < 1000; i++) {
      System.out.printf("main() ~~~~> %d\n", i);
    }
    // main() 메서드의 코드를 모두 실행했다고 해서 JVM이 종료되는 것은 아니다.
    // 다른 스레드의 실행 모두 끝나야만 JVM이 종료된다.
  }

}







