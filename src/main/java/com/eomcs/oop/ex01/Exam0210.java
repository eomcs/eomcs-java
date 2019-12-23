package com.eomcs.oop.ex01;

//# 레퍼런스 배열 - 사용 전
//
public class Exam0210 {
  public static void main(String[] args) {
    // 여러 개의 인스턴스 주소 저장하기

    class Score {
      String name;
      int kor;
      int eng;
      int math;
      int sum;
      float aver;
    }

    // Score 레퍼런스를 여러 개 준비한다. 
    Score s1, s2, s3;

    // 각 레퍼런스에 인스턴스를 만들어 주소를 저장한다.
    s1 = new Score();
    s2 = new Score();
    s3 = new Score();

    // 레퍼런스를 이용하여 인스턴스에 접근한 다음에 항목에 값 넣는다.
    s1.name = "홍길동";
    s2.name = "임꺽정";
    s3.name = "유관순";

    // 레퍼런스를 통해 인스턴스의 각 항목 값을 꺼낸다.
    System.out.printf("이름: %s\n", s1.name);
    System.out.printf("이름: %s\n", s2.name);
    System.out.printf("이름: %s\n", s3.name);
  }
}
