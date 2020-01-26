package com.eomcs.oop.ex02;

// # 사용자 정의 데이터 타입 + 인스턴스 메서드
//
public class Exam0130 {

  static class Score {
    String name;
    int kor;
    int eng;
    int math;
    int sum;
    float average;

    // 클래스 메서드로 연산자를 정의하면,
    // - 계산을 수행할 때마다 인스턴스의 주소를 파라미터로 받아야 한다.
    // - 매우 번거롭다.
    //
    // public static void calculate(Score score) {
    //   score.sum = score.kor + score.eng + score.math;
    //   score.average = score.sum / 3f;
    // }
    // - 그래서 자바는 "인스턴스 메서드"라는 것을 제공한다.
    //

    // 인스턴스 메서드
    // - 메서드가 호출할 때 인스턴스의 주소를 파라미터로 넘기지 않는다.
    // - 메서드를 호출할 때(연산자를 사용할 때), 메서드 앞에 인스턴스 주소를 적는다.
    // - 이렇게 전달된 인스턴스 주소는 메서드에 내장된 this라는 변수에 자동 복사된다.
    // - 그래서 파라미터 대신 this를 사용하면 된다.
    // - 인스턴스 메서드는 static을 붙이지 않는다.
    //
    public void calculate() {
      this.sum = this.kor + this.eng + this.math;
      this.average = this.sum / 3f;
    }
  }


  public static void main(String[] args) {
    Score s = new Score();
    s.name = "홍길동";
    s.kor = 100;
    s.eng = 90;
    s.math = 85;

    // 다음은 Score의 값을 다루기 위해 non-instance 메서드를 호출하는 예이다.
    // => non-instance 메서드 = static 메서드 = 클래스 메서드
    /*
     * Score.calculate(score);
     */

    // 클래스 메서드를 사용할 때 마다 매번 인스턴스의 주소를 파라미터로 넘겨줘야 했다.
    // 그러나 인스턴스 메서드를 사용하면 인스턴스 주소를 넘기기가 더 편하다.
    // 메서드 호출 앞에다 둔다.
    // 훨씬 소스 코드의 목적을 이해하는데 직관적이다.
    s.calculate(); // 변수 뒤에 연산자를 놓는 i++ 의 예와 비슷하다.

    System.out.printf("%s, %d, %d, %d, %d, %.1f\n", s.name, s.kor, s.eng, s.math, s.sum, s.average);
  }
}

// 클래스 메서드 vs 인스턴스 메서드
// 1) 클래스 메서드
// - 메서드 선언부에 static이 붙는다.
// - 특정 인스턴스가 아닌 모든 인스턴스에 대해 작업을 수행할 때 사용하는 메서드이다.
// - 사용법
// 클래스명.메서드명();
//
// - 다음과 같이 레퍼런스를 통해서도 호출할 수 있다.
// 레퍼런스.메서드명();
// 그러나 가능한 레퍼런스를 사용하여 클래스 메서드를 호출하지 말라! 다른 개발자가 헷갈려 한다.
//
// 2) 인스턴스 메서드
// - 메서드 선언부에 static이 붙지 않는다.
// - 특정 인스턴스에 대해 작업을 수행할 때 사용하는 메서드이다.
// - 사용법
// 레퍼런스.메서드명();
//
// 정리!
// 사용자 정의 데이터 타입에서
// 인스턴스의 값을 다루는 연산자를 정의할 때는
// 인스턴스 메서드로 만드는 것이 사용하기 편하다!


