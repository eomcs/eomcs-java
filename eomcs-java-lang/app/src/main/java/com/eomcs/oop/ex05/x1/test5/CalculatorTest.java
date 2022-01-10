package com.eomcs.oop.ex05.x1.test5;

// 기능 추가:
//   - 곱하기 계산 기능을 수행
// 해결책?
//   - 기존의 Calculator 클래스와 연결한 후 위임을 통해 기존 기능을 처리하고 새 기능을 수행한다.
public class CalculatorTest {
  public static void main(String[] args) {
    Calculator2 c = new Calculator2();

    c.plus(100);
    c.minus(200);
    c.multiple(2);

    System.out.println(c.getResult());
  }
}

// 위임(delegation)
// - 상속은 문법적으로 기능을 확장하기 때문에 코드가 경직되어 있다.
// - 코드가 경직되어 있다?
//   => 다음과 같이 클래스가 계층을 이루고 있다고 가정한다.
//        A <-- B <-- C
//      즉 B는 A를 상속 받고, C는 B를 상속 받는다.
//   => C의 기능이 필요한 D는 다음과 같이 C를 상속 받을 것이다.
//        C <-- D
//   => 여기서 D는 B의 기능이 필요없음에도 어쩔 수 없이 상속 받아야 한다.
//      왜? C가 B를 상속 받기 때문이다.
//      이것이 코드가 경직되었다는 뜻이다.
//      필요없는 기능을 자유롭게 뺄 수 없다. 유연성이 부족하다.
//










