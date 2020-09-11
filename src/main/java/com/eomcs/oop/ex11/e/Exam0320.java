// anonymous class - 수퍼 클래스의 메서드 오버라이딩 하기
package com.eomcs.oop.ex11.e;

public class Exam0320 {
  // 클래스는 스태틱으로 선언해야만 스태틱 멤버에서 접근할 수 있다.
  static class A {

    String name;

    public A() {
      name = "이름 없음";
    }

    public A(final String name) {
      this.name = name;
    }

    public void print() {
      System.out.println(name);
    }
  }

  public static void main(final String[] args) {
    final A obj = new A() {
      // 수퍼 클래스의 메서드를 오버라이딩 한다.
      @Override
      public void print() {
        System.out.printf("'%s'님 반갑습니다!", name);
      }
    };
    obj.print();

  }
}
