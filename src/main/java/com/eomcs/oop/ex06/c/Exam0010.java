// 오버라이딩(overriding) - 오버라이딩을 하는 이유?
package com.eomcs.oop.ex06.c;

class Score {

  protected String name;
  protected int kor;
  protected int eng;
  protected int math;
  protected int sum;
  protected float aver;

  public int getSum() {
    return this.sum;
  }

  public float getAver() {
    return this.aver;
  }

  public void compute() {
    this.sum = this.kor + this.eng + this.math;
    this.aver = this.sum / 3f;
  }
}

class Score2 extends Score {
  protected int music;
  protected int art;

  // 오버라이딩(overriding)?
  // => 상속 받은 메서드를 서브 클래스의 역할에 맞게 재정의하는 문법
  // => 상속 받은 메서드와 똑 같은 시그너처로 메서드를 정의한다.
  // => 오버라이딩 메서드는 원래의 메서드 보다 접근 범위가 같거나 커야 한다.
  //    원래의 메서드 보다 접근 범위가 줄어들면 안된다.

  @Override
  // - 컴파일러에게 상속 받은 메서드를 재정의 한다는 것을 알려준다.
  // - 만약 개발자가 메서드 이름을 다르게 사용하거나 파라미터를 잘못 선언하면
  // - 컴파일러가 즉시 알려준다.
  public void compute() {
    this.sum = this.kor + this.eng + this.math + this.music + this.art;
    this.aver = this.sum / 5f;
  }
}

public class Exam0010 {
  public static void main(String[] args) {

    Score2 score = new Score2();

    score.kor = 100;
    score.eng = 100;
    score.math = 100;
    score.music = 100;
    score.art = 100;

    // 새로 추가한 필드에 맞춰 계산이 달라야 한다.
    score.compute();

    System.out.printf("%d(%f)\n", score.getSum(), score.getAver());

  }
}
