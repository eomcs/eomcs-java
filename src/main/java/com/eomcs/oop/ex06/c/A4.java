package com.eomcs.oop.ex06.c;

public class A4 extends A3 {
  // 필드 오버라이딩
  String age;

  // this.필드명 => 현재 클래스에서 해당 필드를 찾는다. 없으면 상위 클래스로 따라 올라가면서 찾는다.
  // super.필드명 => 상위 클래스에서부터 해당 필드를 찾는다. 없으면 계속 상위 클래스로 따라 올라간다.
  void print3() {
    System.out.printf("'%s(%s, %d)'님 반갑습니다!\n", this.name, this.age, super.age);
  }
}







