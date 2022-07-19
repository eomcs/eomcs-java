package com.eomcs.oop.ex02;

//0) 낱개 변수 사용
//1) 성적 데이터를 저장할 사용자 정의 데이터 타입을 만든다.
//
public class Exam0110 {

  public static void main(String[] args) {

    // ## 사용자 정의 데이터 타입 만들기
    // - 학생의 성적 데이터를 담을 메모리(변수)를 설계한다.
    //
    class Score {
      // 인스턴스 변수(instance variable; instance field)
      // - new 명령으로 생성되는 변수이다.
      // - 데이터를 개별적으로 다루고 싶을 때 인스턴스 변수로 선언한다.
      //
      String name; // 변수 또는 필드
      int kor;
      int eng;
      int math;
      int sum;
      float aver;
    }

    // 사용자 정의 데이터 타입을 사용하는 방법
    // - new 명령을 사용하여 설계도에 기술된 대로 메모리(변수)를 준비한다.
    // - 변수는 Heap 영역에 생성된다.
    // - 변수들이 생성된 메모리의 주소를 레퍼런스(주소 변수)에 저장한다.
    Score s1 = new Score();

    // - 클래스로 만든 메모리는 레퍼런스를 통해 접근한다.
    s1.name = "홍길동";
    s1.kor = 100;
    s1.eng = 90;
    s1.math = 85;
    s1.sum = s1.kor + s1.eng + s1.math;
    s1.aver = (float) s1.sum / 3;

    System.out.printf("%s: %d, %d, %d, %d, %.1f\n", 
        s1.name, s1.kor, s1.eng, s1.math, s1.sum, s1.aver);

    Score s2 = new Score();
    s2.name = "임꺽정";
    s2.kor = 90;
    s2.eng = 80;
    s2.math = 75;
    s2.sum = s2.kor + s2.eng + s2.math;
    s2.aver = (float) s2.sum / 3;

    System.out.printf("%s: %d, %d, %d, %d, %.1f\n", s2.name, 
        s2.kor, s2.eng, s2.math, s2.sum, s2.aver);

    Score s3 = new Score();
    s3.name = "유관순";
    s3.kor = 80;
    s3.eng = 70;
    s3.math = 65;
    s3.sum = s3.kor + s3.eng + s3.math;
    s3.aver = (float) s3.sum / 3;

    System.out.printf("%s: %d, %d, %d, %d, %.1f\n", 
        s3.name, s3.kor, s3.eng, s3.math, s3.sum, s3.aver);
  }
}

// 클래스 문법의 용도?
// 1) 사용자 정의 데이터 타입 만들 때
//    즉 새로운 구조의 메모리를 설계할 때 사용한다.
// 2) 메서드를 묶을 때
//    서로 관련된 기능을 관리하기 쉽게 묶고 싶을 때 사용한다.


