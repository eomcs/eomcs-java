// 생성자 : 특정 생성자 정보 가져오기
package com.eomcs.reflect.ex04;

import java.lang.reflect.Constructor;

public class Exam02 {

  public Exam02() {}
  public Exam02(int i) {}
  public Exam02(String s, int i) {} 

  public static void main(String[] args) throws Exception {
    Class clazz = Exam02.class;

    // 특정 생성자만 가져오기
    Constructor c = clazz.getConstructor(int.class);
    System.out.printf("%s(%d)\n", c.getName(), c.getParameterCount());

  }

}
