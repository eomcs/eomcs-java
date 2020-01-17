// local class - 로컬 클래스가 로컬 변수를 조회용으로만 사용하는 이유?
package com.eomcs.oop.ex11.a;

public class Exam0360 {


  public static void main(final String[] args) {
    final Exam0360 obj = new Exam0360();
    final Runner r = obj.createRunner("홍길동");
    // createRunner()의 리턴 값은
    // 이 메서드에 선언된 로컬 클래스인 A의 객체이다.
    // A 객체는 Runner 규칙에 따라 만들었기 때문에
    // Runner 레퍼런스에 저장할 수 있다.
    // 이 레퍼런스를 사용하여 A 객체의 run() 메서드를 호출해 보자!
    r.run();

    // 로컬 클래스의 객체가 로컬 변수의 값을 조회용으로만 사용하는 이유?
    // => 위의 코드에서 createRunner() 호출이 끝나면 이 메서드가 실행되는 동안
    // 생성되었던 모든 로컬 변수는 스택 메모리에서 제거된다.
    // 즉 createRunner()의 name 파라미터가 제거된다는 것이다.
  }

  Runner createRunner(String name) {

    class A implements Runner {

      @Override
      public void run() {
        System.out.printf("%s님이 달립니다!", name);
      }
    }
    return new A();
  }
}


interface Runner {
  void run();
}
