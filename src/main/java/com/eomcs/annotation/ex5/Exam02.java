// 애노테이션 프로퍼티 값 추출 - 배열 값 추출
package com.eomcs.annotation.ex5;

public class Exam02 {

  public static void main(String[] args) {
    Class<?> clazz = MyClass2.class;
    MyAnnotation2 obj = clazz.getAnnotation(MyAnnotation2.class);

    System.out.println(obj.v1()[0]);
    System.out.println(obj.v2()[0]);

    float[] values = obj.v3();
    System.out.println(values[0]);

    System.out.println(obj.v1()[1]);
    System.out.println(obj.v2()[1]);
    System.out.println(values[1]);

  }
}


