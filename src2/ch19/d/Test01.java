// static nested class 사용 후 
package ch19.d;

public class Test01 {

  public static void main(String[] args) {
    Product p = new Product();
    p.maker = "비트컴퓨터";
    p.title = "비트마우스";
    p.price = 98000;
    
    // 분류를 지정할 때 OGNL 방식으로 표현할 수 있다.
    // => OGNL(Object Graph Navigation Language)은 객체의 하위 프로퍼티 값에 접근하는 표기법.
    // => 문법:
    //      객체.프로퍼티명.프로퍼티명.프로퍼티명 = 값;
    // => 자바는 기본으로 객체의 필드를 사용할 때 dot(.)을 이용하여 지정한다.
    //    그 하위 필드도 마찬가지로 점(.)을 이용하여 지정할 수 있다.
    //    이런 식으로 점(.)을 이용하여 하위 필드를 지정하는 표기법을 OGNL이라 한다.
    // 
    // => static nested class 를 사용하여 더 극단적으로 분류 값을 OGNL 표기법으로 사용할 수 있다.
    // => 다음과 같이 하나의 이름을 사용하는 대신에 점(.)을 사용하여 하위 프로퍼티를 접근하는 방식은
    //    코드를 읽기 쉽고 유지보수하기 쉽게 도와준다.
    // => 이렇게 클래스 이름으로 필드처럼 사용할 수 있어 보기가 쉽다.
    //
    //p.category = Category.COMPUTER_MOUSE_BLUETOOTH; // 예전의 상수를 사용하는 방식
    p.category = Category.computer.mouse.bluetooth; // static nested class 사용
    
    // static nested class를 이런 식으로 적용한 대표적인 예가
    // 안드로이드 컴파일러가 자동 생성하는 R.java 이다.
    // 
  }

}









