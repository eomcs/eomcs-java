// 오버라이딩(overriding) - 준비
package com.eomcs.oop.ex06.c;

class A {
  String name = "A";
  String tel = "A: 010-1111-1111";
  boolean working = true;

  void print() {
    System.out.println("A.print():");
    System.out.printf("  => this.name(%s)\n",
        this.name);
    System.out.printf("  => this.tel(%s)\n",
        this.tel);
    System.out.printf("  => this.working(%s)\n",
        this.working);
  }
}


class A2 extends A {
  int age = 20;

  void print2() {
    System.out.println("A2.print2():");
    System.out.printf("  => this.name(%s), super.name(%s)\n",
        this.name, super.name);
    System.out.printf("  => this.tel(%s), super.tel(%s)\n",
        this.tel, super.tel);
    System.out.printf("  => this.working(%s), super.working(%s)\n",
        this.working, super.working);
    System.out.printf("  => this.age(%s), super.age(컴파일 오류!) \n",
        this.age /*, super.age*/);
  }
}


class A3 extends A {
  int age = 30;

  // 필드를 오버라이딩 할 수 있다.
  String tel = "A3: 010-1111-2222";

  // 상속 받은 메서드가 서브 클래스의 역할에 맞지 않다면
  // 서브 클래스의 역할에 맞춰서 재정의 하라!
  // 재정의?
  // => 즉 부모의 메서드와 똑같은 메서드를 정의하는 것이다.
  // => 그래서 부모의 메서드를 덮어쓰는(override) 것이다.
  // => 이 메서드를 호출하는 개발자 입장에서는
  //    A 클래스와 같은 이름으로 메서드를 호출하기 때문에
  //    별다른 구분없이 사용할 수 있다.
  // => 부모 클래스의 메서드와 같은 시그너처(signature)를 갖는
  //    메서드를 만든다.
  //    메서드 시그너처(method signature) = 함수 프로토타입(function prototype)
  //       메서드명, 파라미터 타입/개수, 리턴 타입
  @Override
  void print() {
    System.out.println("A3.print():");
    System.out.printf("  => this.name(%s), super.name(%s)\n", this.name, super.name);
    System.out.printf("  => this.tel(%s), super.tel(%s)\n", this.tel, super.tel);
    System.out.printf("  => this.working(%s), super.working(%s)\n", this.working, super.working);
    System.out.printf("  => this.age(%s), super.age(컴파일 오류!) \n", this.age /*, super.age*/);
  }
}


class A4 extends A3 {
  // 필드 오버라이딩
  // - 필드 오버라이딩은 메서드와 달리 변수의 타입이 달라도 된다.
  //
  String age = "40";

  boolean working = false;

  // this.필드명 => 현재 클래스에서 해당 필드를 찾는다. 없으면 상위 클래스로 따라 올라가면서 찾는다.
  // super.필드명 => 상위 클래스에서부터 해당 필드를 찾는다. 없으면 계속 상위 클래스로 따라 올라간다.
  void print3() {
    System.out.println("A4.print3():");
    System.out.printf("  => this.name(%s), super.name(%s)\n", this.name, super.name);
    System.out.printf("  => this.age(%s), super.age(%s)\n", this.age, super.age);
    System.out.printf("  => this.tel(%s), super.tel(%s)\n", this.tel, super.tel);
    System.out.printf("  => this.working(%s), super.working(%s)\n", this.working, super.working);
  }
}

public class Exam0440 {

  //오버라이딩(overrding)?
  //=> 부모로부터 상속 받은 메서드 중에서
  //   자신(서브클래스)의 역할에 맞지 않는 메서드가 있다면,
  //   자신(서브클래스)의 역할에 맞춰 상속받은 메서드를 재정의하여
  //   프로그래밍의 일관성을 확보하는 문법이다.
  //

  public static void main(String[] args) {
    // 1) A 클래스 준비
    //    test1();

    // 2) A2 클래스 : 오버라이딩 사용 전
    //    test2();

    // 3) A3 클래스 : 오버라이딩 사용 후
    test3();

  }

  static void test1() {
    A obj1 = new A();
    obj1.print();
  }

  static void test2() {

    A2 obj1 = new A2();
    obj1.print(); // A 클래스의 print() 메서드 호출

    // 아하! 그런데 문제가 있다.
    // A 설계도에 있는 print()는 오직 이름만 출력하게 되어 있다.
    // 이 메서드를 변경해야 하는데,
    // 기존의 A 클래스를 손대지 않고 print()의 기능을 변경할 순 없을까?
    // 만약 없다면?
    // => 다른 이름으로 메서드를 만들어야겠지!
    // => 왜?
    //    오버로딩에서는 파라미터 형식이 같은 메서드를 중복해서 만들 수 없다!
    //    그래서 A2 클래스에서는 print2()라는 메서드를 추가하였다.
    //
    obj1.print2(); // A2 클래스의 print2() 메서드 호출


    // 부모 클래스로부터 상속 받은 메서드를 재정의할 수 없어서
    // 새 메서드를 만들어야 한다면,
    // 같은(또는 유사한) 일을 하는 메서드에 대해
    // 안타깝게도 다른 이름으로 메서드를 만들어야 하기 때문에
    // 개발자는 여러 개의 메서드를 암기해야 하는 번거로움이 생긴다.
    // => 이런 문제를 해결하기 위해 등장한 문법이 "오버라이딩(overriding)"이다!
  }

  static void test3() {
    A3 obj1 = new A3();
    obj1.name = "홍길동";
    obj1.age = 20;

    // A3 클래스에서 A 클래스의 print() 메서드를 재정의 했기 때문에
    // 다음 호출하는 메서드는 A3의 print() 메서드가 된다.
    obj1.print(); // A3에서 재정의한 print() 호출
  }
}
