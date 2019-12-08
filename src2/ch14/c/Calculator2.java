// 오버로딩(overloading) - 상속 받은 메서드와 같은 이름의 메서드를 추가로 정의하는 것도 오버로딩이다.
package ch14.c;

public class Calculator2 extends Calculator {
  
  // 상속 받은 메서드와 이름이 같은 그러나 파라미터가 다른 메서드를 추가로 정의할 수 있다.
  // => 상속 받은 메서드든 현재 클래스에 정의된 메서드든 간에 
  //    같은 이름의 메서드(그러나 파라미터가 다른)를 정의하면 오버로딩이다.
  public void minus(int a, int b) {
    this.result -= (a + b);
  }
}
