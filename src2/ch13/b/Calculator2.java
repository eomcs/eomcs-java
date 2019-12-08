package ch13.b;

import ch13.Calculator;

// 기존 클래스의 기능을 상속 받겠다고 선언한다.
// => 기존 클래스의 기능을 내 것처럼 사용하겠다고 선언하는 것이다.
// => 주의!
//    상속이라는 말이 기존 클래스의 코드를 나 몰래 자동으로 복사해오는 게 아닌가 하는 오해를 많이 한다.
//    아니다! 기존 클래스의 코드를 복제해 오지 않는다.
//    단지 내 것처럼 사용할 수 있는 권한을 획득하는 것이다.
// => 문법
//    class 클래스명 extends 기존클래스명 {...}
// => 용어
//    상속을 해주는 클래스를 "수퍼(super) 클래스", "부모(parent) 클래스" 라 부른다.
//    상속을 받는 클래스를 "서브(sub) 클래스", "자식(child) 클래스" 라 부른다.
//
public class Calculator2 extends Calculator {
  
  // 기존 기능은 Calculator를 통해 사용한다.
  
  // 새 기능은 이 클래스에 추가한다.
  //
  public void multiple(int value) {
    // super 클래스의 result 필드를 사용해야 하는데
    // 만약 그 필드가 private으로 선언되어 있으면 접근할 수 없다.
    // 그래서 현업에서는 나중에 서브 클래스가 직접 접근할 수 있도록 
    // 주로 protected로 선언한다.
    //
    this.result *= value;
  }
  
  public void divide(int value) {
    this.result /= value;
  }
}








