package ch13.a;

// 기존의 클래스에 기능 추가하기
// 방법1) 기존 클래스의 코드를 복/붙해서 처리한다.
public class Calculator2 {
  
  // Calculator 코드를 그대로 복사해 온다.
  private int result;
  
  public int getResult() {
    return this.result;
  }
  
  public void plus(int value) {
    this.result += value;
  }
  
  public void minus(int value) {
    this.result -= value;
  }
  
  // 기능을 추가한다.
  public void multiple(int value) {
    this.result *= value;
  }
  
  public void divide(int value) {
    this.result /= value;
  }
}








