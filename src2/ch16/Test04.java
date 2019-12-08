// 제네릭(generic) 응용 - 클래스 전체에 응용하기
package ch16;

import java.util.Date;

// 제네릭을 적용하지 않은 클래스
class Box1 {
  Object value;
  
  public Box1(Object value) {
    this.value = value;
  }
  
  public Object getValue() {
    return this.value;
  }
}

// 제네릭을 적용한 클래스
// => 클래스 전체에서 사용할 "타입 파라미터(타입 이름을 저장하는 변수)"를 선언하기
// =>   class 클래스명<타입파라미터명, 타입파라미터명, ...> {...}
// => 클래스가 다루는 어떤 타입이 있다고 가정하자. 그 타입을 T라고 부르겠다.
//    
class Box2<T> {
  T value;  // T라는 타입의 인스턴스 주소를 저장할 필드
  
  public Box2(T value) { // T라는 타입의 객체를 받을 파라미터
    this.value = value;
  }
  
  public T getValue() { // T라는 타입의 객체를 리턴
    return this.value;
  }
}


public class Test04 {
  public static void main(String[] args) {
    
    // 제네릭이 적용되지 않은 Box1 클래스 이용
    Box1 b1 = new Box1(100); // <= Integer.valueOf(100)
    Box1 b2 = new Box1("Hello");
    Box1 b3 = new Box1(new Date());
    
    // Box1 객체에서 값 꺼내기
    // => 값을 꺼낼 때마다 형변환 해야 한다.
    //
    int v1 = (int) b1.getValue(); // <= 리턴값.intValue()
    String v2 = (String) b2.getValue();
    Date v3 = (Date)b3.getValue();
    
    // 제네릭이 적용된 Box2 클래스 이용
    // 먼저 Box2가 어떤 타입의 값을 다룰 것인지 지정해야 한다.
    Box2<String> x1 = new Box2<String>("홍길동");
    
    // 변수 선언에서 이미 어떤 타입을 다룰지 지정했기 때문에 
    // new 를 실행할 때는 굳이 또 타입을 지정할 필요가 없다. 생략해도 된다.
    Box2<String> x2 = new Box2<>("임꺽정");
    
    Box2<String> x3; 
    x3 = new Box2<>("유관순");
    
    // 그런데 제네릭에 지정된 타입이 아닌 것을 넣으려 하면 컴파일 오류가 발생한다.
    // => 이것이 제네릭을 사용하는 이유이기도 하다.
    // => 즉 특정 클래스만 다루도록 제한할 수 있다.
    //Box2<String> x4 = new Box2<>(new Date());// 컴파일 오류!
    
    // 제네릭을 사용하면 getValue() 메서드가 각 타입별로 존재하는 것처럼 사용할 수 있다.
    // => 즉 형변환 할 필요가 없다.
    String y1 = x1.getValue();
    String y2 = x2.getValue();
    String y3 = x3.getValue();
    
    Box2<Date> x4 = new Box2<>(new Date());
    Date y4 = x4.getValue(); // 형변환 할 필요가 없다.
                             // 마치 Date을 리턴하는 getValue() 메서드가 있는 것 같다.
    
    // 제네릭은 다형성의 유연함을 그대로 유지하면서 특정 타입의 데이터를 다루도록 
    // 도와주는 매우 편리한 문법이다.
  }
  
}


