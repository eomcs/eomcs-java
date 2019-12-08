// 중첩 클래스 사용 II : 로컬 클래스와 익명 클래스 
package ch19.a;

class Car {
  String model;
  
  public void run() {
    System.out.println("달린다!");
  }
  
  public Car() {
    this.model = "미정";
  }
  
  public Car(String model) {
    this.model = model;
  }
}

interface Pen {
  void write();
}

public class Test04 {

  public static void main(String[] args) {
    
    class A {}

    // 로컬 클래스 사용 
    A obj = new A();
    
    // 익명 클래스 정의 1:
    // => Car 클래스를 상속 받아 익명 클래스를 정의한다.
    // => 인스턴스를 생성할 때 수퍼 클래스인 Car의 기본 생성자를 호출한다.
    //
    Car obj2 = new Car() {
      @Override
      public void run() {
        System.out.println(this.model + " : 달려라! 달려라!");
      }
    }; // new 명령은 인스턴스를 생성하는 명령이기 때문에 문장 끝에 세미콜론(;)을 붙여야 한다.
    obj2.run();
    
    // 익명 클래스 정의 2:
    // => 클래스 이름 다음에 오는 것이 수퍼 클래스의 생성자를 지정하는 문법이다.
    // => 수퍼 클래스의 기본 생성자 대신 다른 생성자 호출하기.
    Car obj3 = new Car("티코") {
      @Override
      public void run() {
        System.out.println(this.model + " : 경차 달린다!");
      }
    };
    obj3.run();
    
    // 익명 클래스 정의 3: 
    // => 인터페이스를 구현하여 익명 클래스를 만들기
    // => 인터페이스를 지정하면 수퍼 클래스는 자동으로 Object가 된다.
    // => 그러면 호출할 생성자도 Object 클래스의 생성자이다.
    //
    Pen obj4 = new Pen() {
      @Override
      public void write() {
        System.out.println("글 쓴다!");
      }
    };
    obj4.write();
    
    // 주의!
    // => 수퍼 클래스와 인터페이스를 동시에 지정하여 익명 클래스를 만들 수 없다.
    // => 여러 인터페이스를 구현한 익명 클래스를 만들 수 없다.
    //Car obj5 = new Car, Pen() {} // 컴파일 오류! 이런 문법 없다.
    
    
  }
  
  public static void m1() {
    // 다른 메서드(예: main())에 정의된 로컬 클래스를 사용할 수 없다.
    //A obj = new A(); // 컴파일 오류! 
  }

}







