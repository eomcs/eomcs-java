package com.eomcs.io.ex10;

public class Member3 implements java.io.Serializable {

  // java.io.Serializable 인터페이스를 구현하는 모든 클래스는 
  // 반드시 시리얼 데이터의 버전을 식별할 때 사용할 번호를 지정해야 한다.
  // 버전 번호는 private static final long serialVersionUID 변수에 저장한다.
  // 만약 클래스에 그 변수가 없다면 컴파일러가 자동으로 부여한다.
  //
  private static final long serialVersionUID = 7;

  String name;
  int age;
  boolean gender; // true(여자), false(남자)

  // Exam03_2.java 를 실행하기 전에 다음 변수를 추가하고, toString()을 개정하라!
  String tel;

  @Override
  public String toString() {
    return "Member3 [name=" + name + ", age=" + age + ", gender=" + gender + ", tel=" + tel + "]";
  }


}
