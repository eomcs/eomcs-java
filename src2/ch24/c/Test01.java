// 스레드 정의하기
package ch24.c;

public class Test01 {

  // 1) Thread 클래스를 상속 받아 정의하기
  // => 구현하기 편하다.
  // => 그런데 다중 상속이 불가능하기 때문에 다른 클래스를 상속 받을 수 없다.
  // => 즉 MyThread가 다른 클래스를 상속 받으면서 스레드가 될 순 없다.
  static class MyThread extends Thread {
    // 스레드에서 독립적으로 실행할 코드를 run() 메서드에 작성하라!
    @Override
    public void run() {
      for (int i = 0; i < 2000; i++) {
        System.out.printf("MyThread ===> %d\n", i);
      }
    }
  }
  
  // 2) Runnable 인터페이스를 구현하기
  // => 실무에서 스레드를 만들 때 많이 사용한다.
  // => 인터페이스를 구현하는 것이기 때문에 다른 클래스를 상속 받을 수 있다.
  // => 직접적으로 스레드가 아니기 때문에 실행할 때는 Thread의 도움을 받아야 한다.
  static class MyWork implements Runnable {
    // 스레드가 독립적으로 실행할 코드를 run() 메서드 안에 작성한다.
    @Override
    public void run() {
      for (int i = 0; i < 2000; i++) {
        System.out.printf("MyWork >>>>> %d\n", i);
      }
    }
  }
  
  public static void main(String[] args) {
    
    // 스레드 실행
    // 1) Thread의 서브 클래스는 그냥 인스턴스를 만들어 start()를 호출한다.
    MyThread t1 = new MyThread();
    t1.start(); // run() 메서드를 호출한 후 즉시 리턴한다. 비동기로 동작한다.
    
    // 2) Runnable 구현체는 인스턴스를 만들어 Thread 객체의 생성자에 넘겨주고, 
    //    스레드의 start()를 호출한다.
    MyWork w = new MyWork();
    Thread t2 = new Thread(w);
    t2.start(); // 생성자에 넘겨준 MyWork의 run() 메서드를 호출한 후 즉시 리턴한다. 비동기로 동작한다.
    
    for (int i = 0; i < 2000; i++) {
      System.out.printf("main ~~~~> %d\n", i);
    }
    
    // 스레드에게 CPU 사용을 배분할 때 순서대로 하지 않는다.
    // OS의 CPU 스케줄링 정책에 따라 스레드가 실행된다.
    // 즉 JVM에서 스레드를 실행하는 것이 아니라 OS가 실행한다.
    // 결론!
    // => 똑 같은 자바의 스레드 코드가 OS에 따라 실행 순서가 달라질 수 있다.
    // 
    // 우선 순위로 조정하면 되지 않나요?
    // => Windows OS의 경우 우선 순위(priority) 값이 실행 순서나 실행 회수에 큰 영향을 끼치지 않는다.
    //    그래서 우선 순위의 값을 조정하여 스레드의 실행 회수를 조정하려 해서는 안된다.
    // => 왜? OS에 따라 실행 정책이 다르기 때문이다.
    // => 그냥 특정 코드를 동시에 실행하고 싶을 때 스레드를 사용한다고 생각하라!
  }

}







