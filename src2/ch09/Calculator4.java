package ch09;

public class Calculator4 {
  
  int result = 0; 
     
  // 인스턴스 주소를 파라미터로 받지 않고 호출될 때 앞에서 받는 방법!
  void plus(int a) {
    // 메서드를 호출할 때 인스턴스 주소를 앞으로 넘겼다.
    // 예) c1.plus(2);
    // => 그럼 c1에 들어 있던 인스턴스 주소가 plus에 전달되었다는 것인데,
    //    그 주소 어디에 있는가? 알아야지 인스턴스를 쓰든 안쓰든 할 것 아닌가?
    // => static이 붙지 않은 메서드를 인스턴스 메서드라 부른다.
    // => 모든 인스턴스 메서드는 호출될 때 넘겨 받은 인스턴스 주소를 저장하기 위한 
    //    변수를 내장(built-in)하고 있다.
    // => 내장된 변수가 있다고? 그럼 변수의 이름은? "this" 이다.
    // => 스태틱 메서드(클래스 메서드)에는 this 변수가 없다!
    this.result += a; 
  }
  
  void minus(int a) {
    this.result -= a; // 이렇게 result 변수가 있는 인스턴스 주소를 사용하여 계산을 수행한다.
  }
  
  void multiple(int a) {
    this.result *= a;
  }
  
  void divide(int a) {
    // this를 생략할 수 있다.
    // 생략하면 컴파일할 때 this가 자동으로 붙는다.
    result /= a;
  }
}









