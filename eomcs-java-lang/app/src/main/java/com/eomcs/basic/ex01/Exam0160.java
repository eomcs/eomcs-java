// Object 클래스 - getClass() : 해당 클래스의 정보를 리턴한다.
package com.eomcs.basic.ex01;


public class Exam0160 {

  static class My {
  }

  public static void main(String[] args) {
    My obj1 = new My();

    // 레퍼런스를 통해서 인스턴스의 클래스 정보를 알아낼 수 있다.
    Class classInfo = obj1.getClass();

    // 클래스 정보로부터 다양한 값을 꺼낼 수 있다.
    System.out.println(classInfo.getName()); // 패키지명 + 바깥 클래스명 + 클래스명
    System.out.println(classInfo.getSimpleName()); // 클래스명

  }
}







