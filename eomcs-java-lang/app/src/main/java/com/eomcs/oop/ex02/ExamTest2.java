package com.eomcs.oop.ex02;

import com.eomcs.oop.ex02.util.Calculator2;

// 1) 관련 메서드를 클래스로 묶는다.
// 2) 메서드에서 계산한 결과를 클래스 변수에 저장한다.
// 3) 인스턴스 변수로 바꿔서 결과를 개별적으로 관리한다.
// 4) 인스턴스 메서드로 바꿔서 인스턴스 주소를 this 변수에 받는다.
// 5) 클래스를 별도의 소스 파일로 분리한다.
// 6) 클래스를 패키지로 분류한다.
public class ExamTest2 {

  public static void main(String[] args) {

    // 2 + 3 - 1 * 7 / 3 = ?
    // 3 * 100 / 2 - 17 = ?

    Calculator2 c1 = new Calculator2();
    Calculator2 c2 = new Calculator2();

    c1.plus(2);
    c2.plus(3);
    c1.plus(3);
    c2.multiple(100);
    c1.minus(1);
    c2.divide(2);
    c1.multiple(7);
    c2.minus(17);
    c1.divide(3);

    System.out.printf("result = %d\n", c1.getResult());
    System.out.printf("result = %d\n", c2.getResult());
  }

}


