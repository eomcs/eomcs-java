package com.eomcs.oop.ex01;

//# 레퍼런스와 인스턴스 - 인스턴스 주소 주고 받기
//
public class Exam0320 {
  public static void main(String[] args) {
    class Score {
      String name;
      int kor;
      int eng;
      int math;
      int sum;
      float aver;
    }

    // Score 레퍼런스 선언 + 인스턴스 생성(사용할 메모리 확보) 
    Score s1 = new Score();

    // s1에 저장된 주소를 s2에도 저장한다.
    Score s2 = s1;

    s1.name = "홍길동";

    System.out.println(s2.name);
  }
}
