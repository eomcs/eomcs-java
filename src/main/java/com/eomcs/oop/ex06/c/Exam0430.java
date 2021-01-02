// 오버라이딩(overriding) - this/super의 사용
package com.eomcs.oop.ex06.c;

public class Exam0430 {
  public static void main(String[] args) {
    A4 obj1 = new A4();

    obj1.print3();
    System.out.println("---------------");


    obj1.print();
    System.out.println("---------------");


    System.out.println(obj1.name);
    System.out.println(obj1.age);

    // 레퍼런스에서 super를 사용할 수 없다.
    //System.out.println(obj1.super.age); // 컴파일 오류!
  }
}








