// 오버라이딩(overriding) - 오버라이딩을 하는 이유?
package com.eomcs.oop.ex06.c;

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
