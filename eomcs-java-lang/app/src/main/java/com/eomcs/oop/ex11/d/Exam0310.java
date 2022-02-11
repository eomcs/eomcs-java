// local class에서 바깥 메서드의 로컬 변수 접근
package com.eomcs.oop.ex11.d;

// 계산기 사용법을 정의한다.
interface Calculator {
  double compute(int money);
}

class CalculatorFactory {

  static Calculator create(float interest) {

    class CalculatorImpl implements Calculator {

      @Override
      public double compute(int money) {
        return money + (money * interest);
        // interest는 create() 함수의 로컬 변수이다.
        // CalculatorImpl 객체를 생성하여 리턴한 후에는 interest 로컬 변수는 스택에서 사라진 상태일 것이다.
        // 나중에 compute()를 호출할 때 interest 변수는 없을텐데, 어떻게 된 것인가?
        // => 로컬 클래스에서 메서드의 로컬 변수를 사용한다면
        //    컴파일러는 로컬 클래스에 바깥 메서드의 로컬 변수 값을 저장할 필드를 추가한다.
        //    또한 로컬 클래스의 객체를 생성할 때 생성자에 로컬 변수의 값을 넘겨 줄 것이다.
      }
    }

    return new CalculatorImpl();
  }
}

public class Exam0310 {

  public static void main(String[] args) {
    Calculator c1 = CalculatorFactory.create(0.02f);
    Calculator c2 = CalculatorFactory.create(0.08f);

    System.out.printf("%.2f\n", c1.compute(1235_0000));
    System.out.printf("%.2f\n", c2.compute(1235_0000));
  }

}
