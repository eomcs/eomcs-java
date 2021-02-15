// anonymous class : 로컬 클래스 vs 익명 클래스
package com.eomcs.oop.ex11.e;

public class Exam0110 {
  // 인터페이스의 경우 static으로 선언하지 않아도 스태틱 멤버에서 사용할 수 있다.
  // => 인터페이스는 규칙을 정의한 것이기 때문에 인스턴스 멤버라는 개념이 존재하지 않는다.
  interface A {
    void print();
  }

  public static void main(final String[] args) {

    // 1) 로컬 클래스로 인터페이스 구현하기
    class My implements A {
      String name = "홍길동"; 

      @Override
      public void print() {
        System.out.printf("Hello, %s!\n", this.name);
      }
    }

    A obj = new My();
    obj.print();

    System.out.println("----------------------");

    // 2) 익명 클래스로 인터페이스 구현하기
    // => 인스턴스를 한 번 만 생성할 것이라면,
    //    로컬 클래스로 정의하는 것 보다 익명 클래스로 정의하는 것이 더 낫다.
    // => 특히 객체를 사용하려는 곳에 바로 익명 클래스를 정의하면
    //    읽기 쉽기 때문에 소스 코드를 유지보수 하기가 더 좋다.
    //
    // 익명 클래스로 인터페이스 구현하기
    // 문법:
    // => 인터페이스명 레퍼런스 = new 인터페이스명() {};
    //    - 호출하는 생성자는 수퍼 클래스의 생성자이다.
    //
    A obj2 = new A() {
      String name = "홍길동"; 

      @Override
      public void print() {
        System.out.printf("Hello2, %s!\n", this.name);
      }
    };
    obj2.print();
  }
}

// 익명 클래스를 정의하는 과정
// 1단계 : 로컬 클래스
/*
class X implements A {
  @Override
  public void print() {
    System.out.println("Hello!");
  }
};
 */

// 2단계 : 클래스의 이름을 지운다.
/*
class implements A {
  @Override
  public void print() {
    System.out.println("Hello!");
  }
};

=> 그러나 클래스의 이름이 없기 때문에 인스턴스를 생성할 수가 없다.
A obj = new ();
 */

// 3단계 : 클래스 이름이 없기 때문에 'class', 'implements' 키워드는 없앤다.
/*
A {
  @Override
  public void print() {
    System.out.println("Hello!");
  }
};
 */

// 4단계 : 클래스 이름이 없기 때문에 따로 인스턴스를 생성할 수 가 없다.
//       바로 생성해야 한다.
// => 인터페이스 이름 바로 앞에 'new' 키워드를 둔다.
/*
new A {
  @Override
  public void print() {
    System.out.println("Hello!");
  }
};
 */

// 5단계 : 익명 클래스의 생성자가 없기 때문에 수퍼 클래스의 생성자를 호출한다.
// => 객체 생성할 때 항상 생성자를 호출해야 하는데,
//    클래스에 이름이 없으면 생성자를 만들 수 없다.
//    따라서 호출할 익명 클래스의 생성자가 없다.
//    (실제는 내부에 익명 클래스의 기본 생성자가 만들어진다.)
//    그래서 수퍼 클래스의 생성자를 호출해야 한다.
//    자바의 모든 클래스는 따로 수퍼 클래스를 지정하지 않으면
//    java.lang.Object 클래스가 수퍼 클래스로 자동 설정된다.
//    바로 그 Object 클래스의 생성자를 호출하도록 지정해야 한다.
//    Object 클래스의 생성자는 기본 생성자 하나 뿐이다.
//    인터페이스 이름 뒤에 기본 생성자를 호출하는 괄호를 추가한다.
/*
new A() {
  @Override
  public void print() {
    System.out.println("Hello!");
  }
};
 */

// 6단계 : 익명 클래스의 레퍼런스 선언
// => 익명 클래스는 이름이 없기 때문에
//    익명 클래스로 레퍼런스를 선언할 수 없다.
//    그래서 익명 클래스가 구현한 인터페이스나
//    상속 받는 수퍼 클래스로 레퍼런스를 선언해야 한다.
/*
A obj = new A() {
  @Override
  public void print() {
    System.out.println("Hello!");
  }
};
 */