package com.eomcs.oop.ex02;

//0) 낱개 변수 사용
//1) 성적 데이터를 저장할 사용자 정의 데이터 타입을 만든다.
//2) 리팩토링: 메서드 추출(extract method), static nested class
//3) 리팩토링: 메서드 추출(extract method) = 한 개의 메서드는 한 개의 기능을 수행해야 한다.
//4) GRASP(General Responsibility Assignment Software Patterns) 패턴 
//     => Information Expert: 데이터를 다룰 때는 그 데이터를 갖고 있는 객체에게 묻는다.
//   리팩토링: 메서드 이동(Move Method)
//     => 메서드를 관련된 클래스로 이동시킨다. => 코드의 이해가 쉽다.
// 
public class Exam0140 {

  static class Score {
    String name; 
    int kor;
    int eng;
    int math;
    int sum;
    float aver;

    // 메서드를 이용하여 이 타입의 데이터를 다룰 수 있는 연산자를 정의한다.
    // - 사용자 정의 데이터 타입 입장에서는 메서드가 연산자 역할을 한다.
    // - 즉 사용자 정의 데이터 타입에 메서드를 정의하는 것은
    //   그 데이터를 다룰 연산자를 정의하는 것이다.

    // Score 데이터 값을 다룰 수 있는 새 연산자를 정의해 보자.
    // - 다음 메서드는 Score 객체의 국,영,수 값의 합계와 평균을 계산하는 연산자이다.
    static void compute(Score s) {
      s.sum = s.kor + s.eng + s.math;
      s.aver = (float) s.sum / 3;
    }
    // 클래스 메서드
    // - static이 붙은 메서드이다.
    // - 특정 인스턴스에 대해 사용하는 것이 아니라, 모든 인스턴스에 대해 사용할 수 있다.
    // - 특정 인스턴스의 값을 다루고 싶다면 파라미터로 그 인스턴스의 주소를 받아야 한다.
  }

  public static void main(String[] args) {

    Score s1 = new Score();

    s1.name = "홍길동";
    s1.kor = 100;
    s1.eng = 90;
    s1.math = 85;

    // 다음은 Score의 값을 다루는 연산자가 없을 때의 예이다.
    // core.sum = score.kor + score.eng + score.math; 
    // score.average = score.sum / 3f;

    // 사용자 정의 데이터 타입의 값을 연산자를 사용하여 다뤄보자!
    Score.compute(s1);

    printScore(s1);

    Score s2 = new Score();
    s2.name = "임꺽정";
    s2.kor = 90;
    s2.eng = 80;
    s2.math = 75;
    Score.compute(s2);
    printScore(s2);

    Score s3 = new Score();
    s3.name = "유관순";
    s3.kor = 80;
    s3.eng = 70;
    s3.math = 65;
    Score.compute(s3);
    printScore(s3);
  }

  static void printScore(Score s) {
    System.out.printf("%s: %d, %d, %d, %d, %.1f\n", 
        s.name, s.kor, s.eng, s.math, s.sum, s.aver);
  }


}



