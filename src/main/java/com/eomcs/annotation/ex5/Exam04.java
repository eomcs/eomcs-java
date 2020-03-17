// 애노테이션 프로퍼티 값 추출 - 배열 값 추출
package com.eomcs.annotation.ex5;

public class Exam04 {

  public static void main(String[] args) {
    Class<?> clazz = MyClass4.class;
    MyAnnotation3 obj = clazz.getAnnotation(MyAnnotation3.class);

    System.out.println(obj.v1()[2]);
    System.out.println(obj.v2()[3]);
    System.out.println(obj.v3()[2]);

  }
}


