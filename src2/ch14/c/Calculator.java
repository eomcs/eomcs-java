// 오버로딩(overloading) - 같은 이름의 메서드를 여러 개 정의할 수 있다.
package ch14.c;

public class Calculator {
  int result;
  
  public void plus(int value) {
    this.result += value;
  }
  
  // 오버로딩(overloading)?
  // => 파라미터가 다르더라도 같은 기능을 수행하는 메서드에 대해 같은 이름을 부여함으로써
  //    프로그래밍의 일관성을 유지하게 해주는 문법
  public void plus(int v1, int v2) {
    this.result += v1 + v2;
  }
  
  public void plus(int[] arr) {
    for (int value : arr) {
      result += value;
    }
  }
  
  public void minus(int value) {
    this.result -= value;
  }
}
