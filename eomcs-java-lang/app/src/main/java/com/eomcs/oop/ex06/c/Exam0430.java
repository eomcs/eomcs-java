// 오버라이딩(overriding) - 준비
package com.eomcs.oop.ex06.c;



public class Exam0430 {

  static class A {
    String name = "A";
    String tel = "A: 010-1111-1111";
    boolean working = true;

    void print() {
      System.out.println("A.print():");
      System.out.printf("  => this.name(%s)\n", this.name);
      System.out.printf("  => this.tel(%s)\n", this.tel);
      System.out.printf("  => this.working(%s)\n", this.working);
    }
  }


  static class A2 extends A {
    int age = 20;

    @Override
    void print() {
      System.out.println("A2.print():");
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


  static class A3 extends A {
    int age = 30;
    String tel = "A3: 010-1111-2222";

    @Override
    void print() {
      System.out.println("A3.print():");
      System.out.printf("  => this.name(%s), super.name(%s)\n", this.name, super.name);
      System.out.printf("  => this.tel(%s), super.tel(%s)\n", this.tel, super.tel);
      System.out.printf("  => this.working(%s), super.working(%s)\n", this.working, super.working);
      System.out.printf("  => this.age(%s), super.age(컴파일 오류!) \n", this.age /*, super.age*/);
    }

    void print2() {
      System.out.println("A3.print2():");
      System.out.printf("  => this.name(%s), super.name(%s)\n", this.name, super.name);
      System.out.printf("  => this.tel(%s), super.tel(%s)\n", this.tel, super.tel);
      System.out.printf("  => this.working(%s), super.working(%s)\n", this.working, super.working);
      System.out.printf("  => this.age(%s), super.age(컴파일 오류!) \n", this.age /*, super.age*/);
    }
  }


  static class A4 extends A3 {
    String age = "40";
    boolean working = false;

    @Override
    void print() {
      System.out.println("A4.print():");
      System.out.printf("  => this.name(%s), super.name(%s)\n", this.name, super.name);
      System.out.printf("  => this.age(%s), super.age(%s)\n", this.age, super.age);
      System.out.printf("  => this.tel(%s), super.tel(%s)\n", this.tel, super.tel);
      System.out.printf("  => this.working(%s), super.working(%s)\n", this.working, super.working);
    }
  }

  public static void main(String[] args) {
    A obj1 = new A();
    obj1.print();
    System.out.println("--------------------------------");

    A2 obj2 = new A2();
    obj2.print();
    System.out.println("--------------------------------");

    A3 obj3 = new A3();
    obj3.print();
    System.out.println("--------------------------------");

    A4 obj4 = new A4();
    obj4.print();
    System.out.println("--------------------------------");

    A3 obj5 = new A4();
    obj5.print();
    // 레퍼런스가 실제 자식 객체를 가리킨다면,
    // 메서드를 찾을 때 자식 클래스의 오버라이딩 메서드를 먼저 찾는다.
    // 따라서 obj5의 print()는 실제 obj5가 가리키는 A4의 print()를 호출한다.
    System.out.println("--------------------------------");

    obj5.print2(); // A.print2();
    System.out.println("--------------------------------");
  }
}
