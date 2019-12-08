package ch09.test;

public class Calculator {
  
  // 개별적으로 관리되어야 할 값은 인스턴스 필드에 저장해야 한다.
  int result;
  
  void plus(int a) {
    // 모든 인스턴스 메서드는 호출될 때 넘겨 받은 인스턴스 주소를 
    // 내부에 미리 생성한(built-in) 로컬 변수 this에 보관한다.
    this.result += a;
  }
  
  void minus(int a) {
    result -= a;
  }
  
  void multiple(int a) {
    this.result *= a;
  }
  
  void divide(int a) {
    this.result /= a;
  }

}









