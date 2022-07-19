package com.eomcs.oop.ex02.domain;

public class Score2 {

  public String name; 
  public int kor;
  public int eng;
  public int math;
  public int sum;
  public float aver;

  // new 연산자를 이용하여 인스턴스를 만들 때 자동으로 호출되는 특별한 문법의 메서드
  // => 생성자(constructor)
  //    - 메서드명은 클래스 이름과 같아야 한다.
  //    - 리턴 타입은 없다.
  //    - 오직 new 명령을 실행할 때 호출할 수 있다. 나중에 따로 호출할 수 없다.
  public Score2(String n, int k, int e, int m) {
    this.name = n;
    this.kor = k;
    this.eng = e;
    this.math = m;

    this.compute();
  }

  public void compute() {
    this.sum = this.kor + this.eng + this.math;
    this.aver = (float) this.sum / 3;
  }
}
