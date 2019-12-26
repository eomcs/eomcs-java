package com.eomcs.oop.ex02;

//# 사용자 정의 데이터 타입 - 연산자를 instance method 문법으로 정의
//
public class Score3 {
  String name;
  int kor;
  int eng;
  int math;
  int sum;
  float average; 

  // 다음 연산자는 계산을 수행할 때마다 인스턴스의 주소를 파라미터로 받아야 한다.
  // => 매우 번거롭다. 그래서 자바는 특별한 메서드를 준비하였다.
  //    "인스턴스 메서드"
  /*
    public static void calculate(Score3 score) {
        score.sum = score.kor + score.eng + score.math;
        score.average = score.sum / 3f;
    }
   */

  // 인스턴스 메서드를 사용하여 연산자 정의하기 
  // => 인스턴스의 주소를 파라미터로 받는 것이 아니라,
  //    메서드를 호출할 때(연산자를 사용할 때), 
  //    메서드 앞에 인스턴스의 주소를 적는다.
  // => 이 인스턴스의 주소는 내장된 this라는 변수에 자동 복사된다.
  //    그래서 파라미터 대신 this를 사용하면 된다.
  // => 인스턴스 메서드는 static을 붙이지 않는다.
  public void calculate() {
    this.sum = this.kor + this.eng + this.math;
    this.average = this.sum / 3f;
  }
}

// 클래스 메서드
// => static이 붙는 메서드
// => 특정 인스턴스가 아닌 모든 인스턴스에 대해 작업을 수행할 때 사용하는 메서드이다.
// => 사용법
//    클래스명.메서드명();
//    레퍼런스.메서드명(); 
//    그러나 가능한 레퍼런스를 사용하여 클래스 메서드를 호출하지 말라!
//    다른 개발자가 헷갈려 한다.
// 
// 인스턴스 메서드
// => static이 붙지 않는 메서드
// => 특정 인스턴스에 대해 작업을 수행할 때 사용하는 메서드이다.
// => 사용법
//    레퍼런스.메서드명();






