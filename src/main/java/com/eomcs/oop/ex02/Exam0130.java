package com.eomcs.oop.ex02;

//# 사용자 정의 데이터 타입 - 인스턴스 메서드
//
public class Exam0130 {
  public static void main(String[] args) {
    Score3 score = new Score3();
    score.name = "홍길동";
    score.kor = 100;
    score.eng = 90;
    score.math = 85;

    // 다음은 Score의 값을 다루기 위해 non-instance 메서드를 호출하는 예이다.
    // => non-instance 메서드 = static 메서드 = 클래스 메서드
    /*
        Score2.calculate(score);
     */

    // 클래스 메서드를 사용할 때 마다 매번 인스턴스의 주소를 파라미터로 넘겨줘야 했다.
    // 그러나 인스턴스 메서드를 사용하면 인스턴스 주소를 넘기기가 더 편하다.
    // 메서드 호출 앞에다 둔다.
    // 훨씬 소스 코드의 목적을 이해하는데 직관적이다.
    score.calculate(); // 변수 뒤에 연산자를 놓는 i++ 의 예와 비슷하다.

    System.out.printf("%s, %d, %d, %d, %d, %.1f\n", 
        score.name, score.kor, score.eng, score.math, 
        score.sum, score.average);
  }
}

// 사용자 정의 데이터 타입에서 
// 인스턴스의 값을 다루는 연산자를 정의할 때는
// 인스턴스 메서드로 만드는 것이 사용하기 편하다!









