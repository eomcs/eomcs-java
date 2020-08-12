// 오버라이딩(overriding)
package com.eomcs.oop.ex06.c;

public class A {
  String name;
  String tel = "AAAA";
  boolean working = true;

  void print() {
    System.out.printf("'%s'님 반갑습니다.\n", this.name);
  }
}
