// 클래스에 제네릭(generic) 적용하기 : 적용 후
package com.eomcs.generic.ex01;

import java.util.Date;
import com.eomcs.generic.ex02.Member;

// 2) 제네릭을 적용한 클래스
// - 클래스 전체에서 사용할 "타입 파라미터(타입 이름을 저장하는 변수)"를 선언하기
//      class 클래스명<타입파라미터명, 타입파라미터명, ...> {...}
// - 클래스가 다루는 어떤 타입이 있다고 가정하자. 그 타입을 T라고 부르겠다.
//
class Box<T> {
  T value;  // T라는 타입의 인스턴스 주소를 저장할 필드

  public T get() { // T라는 타입의 객체를 리턴
    return this.value;
  }

  public void set(T value) { // T라는 타입의 객체를 받을 파라미터
    this.value = value;
  }
}

public class Exam0230 {

  public static void main(String[] args) {

    // 2) 제네릭이 적용된 Box 클래스 이용
    // 먼저 Box가 어떤 타입의 값을 다룰 것인지 지정해야 한다.
    Box<Member> b1 = new Box<Member>();

    // 변수 선언에서 이미 어떤 타입을 다룰지 지정했기 때문에
    // new 를 실행할 때는 굳이 또 타입을 지정할 필요가 없다. 생략해도 된다.
    Box<String> b2 = new Box<>();

    // 레퍼런스를 선언한 후에 인스턴스를 할당해도 된다.
    Box<Integer> b3;
    b3 = new Box<>(); // OK!
    //    b3 = new Box<Integer>(); // OK!

    b1.set(new Member("홍길동", 20));
    b2.set("Hello");
    b3.set(100);

    // 제네릭을 사용하면 get() 메서드가 각 타입별로 존재하는 것처럼 사용할 수 있다.
    // => 즉 형변환 할 필요가 없다.
    Member v1 = b1.get();
    String v2 = b2.get();
    Integer v3 = b3.get();

    // 제네릭을 사용하면 제네릭에 지정된 타입이 아닌 것을 넣으려 하면 컴파일 오류가 발생한다.
    // => 이것이 제네릭을 사용하는 이유이기도 하다.
    // => 즉 특정 클래스만 다루도록 제한할 수 있다.
    //
    Box<Date> b4 = new Box<>();
    //    b4.set("Hello!"); // 컴파일 오류!

    // 제네릭 클래스를 사용할 때,
    // 타입 파라미터의 값을 빼면 경고가 뜬다.
    // 내부적으로는 Object를 타입으로 지정한 것으로 다룬다.
    // 즉 다음 코드는 Box b5 = new Box<Object>() 와 같다.
    //
    Box b5 = new Box(); // 경고!

    // 제네릭은 다형성의 유연함을 그대로 유지하면서 특정 타입의 데이터를 다루도록
    // 도와주는 매우 편리한 문법이다.

    // 결론:
    // => 제네릭 문법을 사용하면 Object 타입을 사용하는 것 보다 편리하다.
    // => 각 타입 별로 클래스를 따로 정의한듯한 효과가 있다.
  }
}