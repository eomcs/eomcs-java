package ch08;

// 계산 기능과 관련된 메서드를 별도의 블록으로 분리할 때 사용하는 문법이 "클래스"이다.
public class Calculator {
  static int plus(int a, int b) {
    return a + b;
  }
  
  static int minus(int a, int b) {
    return a - b;
  }
  
  static int multiple(int a, int b) {
    return a * b;
  }
  
  static int divide(int a, int b) {
    return a / b;
  }
  
  static int abs(int a) {
    /*
    if (a >= 0)
      return a;
    else 
      return a * -1;
    */
    return (a >= 0) ? a : a * -1;
  }
  
  // 클래스를 정의하면서 만든 기능을 간단하게 확인하고 싶을 때, 
  // 다음과 같이 해당 클래스에 main() 메서드를 만들어 
  // 테스트 해 볼 수 있다.
  //
  /*
  public static void main(String[] args) {
    System.out.println(abs(100));
    System.out.println(abs(-100));
  }
  */
}









