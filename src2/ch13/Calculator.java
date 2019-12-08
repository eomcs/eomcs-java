package ch13;

public class Calculator {
  
  // 이 필드를 갖고 있는 서브 클래스가 접근할 수 있도록 허락한다.
  protected int result;
  
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
