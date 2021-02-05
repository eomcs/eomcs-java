// 오버라이딩(overriding) - 필드 및 메서드 오버라이딩
package com.eomcs.oop.ex06.c;

public class Exam0140 {

  static class A {
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

  static class A4 extends A {
    // 필드 오버라이딩
    // - 필드 오버라이딩은 메서드와 달리 변수의 타입이 달라도 된다.
    //
    String working = "미취업";

    // this.필드명
    // - 현재 클래스에서 해당 필드를 찾는다. 없으면 상위 클래스로 따라 올라가면서 찾는다.
    // super.필드명
    // - 상위 클래스에서부터 해당 필드를 찾는다. 없으면 계속 상위 클래스로 따라 올라간다.
    @Override
    void print() {
      System.out.println("A4.print():");
      System.out.printf("  => this.name(%s)\n", this.name);
      System.out.printf("  => this.tel(%s)\n", this.tel);
      System.out.printf("  => this.working(%s)\n", this.working);
      System.out.printf("  => super.working(%b)\n", super.working);
    }
  }


  public static void main(String[] args) {
    A4 obj = new A4();
    obj.print(); 
    // A4에서 재정의한 print() 호출

  }
}
