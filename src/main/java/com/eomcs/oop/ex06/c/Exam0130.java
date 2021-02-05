// 오버라이딩(overriding) - 필드 오버라이딩
package com.eomcs.oop.ex06.c;

public class Exam0130 {

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
  }


  public static void main(String[] args) {
    A4 obj = new A4();
    obj.print(); 
    // A의 print() 호출 
    // - A4 가 오버라이딩 한 필드를 사용하지 않는다.
    // - 필드 오버라이딩은 그냥 새 필드를 추가한 것과 같다.


  }
}
