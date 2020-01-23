package com.eomcs.oop.ex01;

// # 레퍼런스 배열 - 사용 전
//
public class Exam0210 {
  public static void main(String[] args) {
    // 여러 개의 인스턴스 주소 저장하기

    // 1) 성적 정보를 저장할 메모리를 설계한다.
    class Score {
      String name;
      int kor;
      int eng;
      int math;
      int sum;
      float aver;
    }

    // 2) 메모리 주소를 저장할 레퍼런스를 준비한다.
    Score s1, s2, s3;

    // 3) 설계도에 따라 메모리를 준비하고 그 주소를 각 레퍼런스에 저장한다.
    s1 = new Score();
    s2 = new Score();
    s3 = new Score();

    // 4) 각각의 레퍼런스를 통해 인스턴스에 접근하여 특정 항목에 값을 넣는다.
    s1.name = "홍길동";
    s2.name = "임꺽정";
    s3.name = "유관순";

    // 5) 각각의 레퍼런스를 통해 인스턴스에 접근하여 특정 항목의 값을 꺼낸다.
    System.out.printf("이름: %s\n", s1.name);
    System.out.printf("이름: %s\n", s2.name);
    System.out.printf("이름: %s\n", s3.name);
  }
}
