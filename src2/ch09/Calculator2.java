package ch09;

public class Calculator2 {
  
  // plus(), minus(), multiple(), divide()가 사용할 변수는 같은 클래스에 둔다.
  // => 클래스 멤버가 사용하는 경우 클래스 멤버로 만들어야 한다.
  static int result = 0; // <== 클래스 필드(스태틱 필드)
  
  static void plus(int a) {
    //this.result = 100; //컴파일 오류! 스태틱 메서드는 this 변수를 내장하고 있지 않다.
    result += a;
  }
  
  static void minus(int a) {
    result -= a;
  }
  
  static void multiple(int a) {
    result *= a;
  }
  
  static void divide(int a) {
    result /= a;
  }
}









