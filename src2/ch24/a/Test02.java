// 멀티 스레드 적용 후 
package ch24.a;

public class Test02 {

  static class MyThread extends Thread {
    @Override
    public void run() {
      // 스레드에 코드는 비동기로 동작한다.
      // 다른 스레드와 별개로 실행한다.
      for (int i = 0; i < 1000; i++) {
        System.out.println("====> " + i);
      }
    }
  }
  
  public static void main(String[] args) {
    
    // 스레드는 비동기로 동작한다.
    // 스레드에 작업을 시킨 후, 그 스레드가 작업이 끝날 때까지 기다리지 않고 즉시 리턴한다.
    // 따라서 스레드이 작업과 main()의 코드가 병행(concurrent)으로 실행한다.
    MyThread t = new MyThread();
    t.start();
    
    for (int i = 0; i < 1000; i++) {
      System.out.println(">>>>> " + i);
    }

  }

}
