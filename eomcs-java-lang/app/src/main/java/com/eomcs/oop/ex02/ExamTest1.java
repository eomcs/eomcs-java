package com.eomcs.oop.ex02;

// 1) 사용자 정의 데이터 타입을 만든다.
// 2) 인스턴스 값을 다룰 calculate() 연산자를 스태틱 메서드로 만든다.
// 3) calculate() 연산자를 논스태틱 메서드로 바꾼다.
//
public class ExamTest1 {

  static class Score {
    String name;
    int kor;
    int eng;
    int math;
    int sum;
    float average;
  }

  public static void main(String[] args) {
    Score score = new Score();

    score.name = "홍길동";
    score.kor = 100;
    score.eng = 90;
    score.math = 85;
    score.sum = score.kor + score.eng + score.math;
    score.average = score.sum / 3f;

    System.out.printf("%s, %d, %d, %d, %d, %.1f\n", score.name, score.kor, score.eng, score.math,
        score.sum, score.average);
  }
}


