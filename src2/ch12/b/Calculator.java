package ch12.b;

public class Calculator {
  
  // 외부의 접근을 막아야 하는 멤버에 대해서는 private modifier를 붙인다.
  // modifier(변경자, 한정자, 제한자)? 원래의 특성을 변경한다.
  // => 원래 특성: private을 붙이지 않으면 같은 패키지의 클래스가 접근하는 것을 허용한다.
  // => 특성 변경: private을 붙이면 오직 같은 클래스의 멤버 만이 접근할 수 있다.
  private int result;
  
  // result 필드의 접근을 제한했기 때문에 result 필드의 값을 리턴해주는 메서드가 필요하다.
  // => 보통 필드의 값을 리턴하는 메서드의 이름을 getXxx라고 짓는다.
  // => 그래서 이런 메서드를 "getter"라 부른다.
  public int getResult() {
    return this.result;
  }
  
  public void plus(int value) {
    this.result += value;
  }
  
  public void minus(int value) {
    this.result -= value;
  }
}
