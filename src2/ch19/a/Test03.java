// 중첩 클래스 사용 I : static nested class와 inner 클래스 사용
package ch19.a;

class X {
   
  static class A {}
  
  class B {}
  
  static void m1() {
    // 같은 스태틱 멤버이기 때문에 static nested class를 바로 사용할 수 있다.
    A obj = new A();
    
    // 스태틱 멤버는 인스턴스 멤버를 바로 사용할 수 없다.
    B obj2; // 레러펀스를 선언할 때는 바로 사용할 수 있다.
    //obj2 = new B(); // 컴파일 오류!
    
    // 컴파일 오류가 발생한 이유?
    // => 다음과 같이 스태틱 메서드에서 인스턴스 메서드를 호출하지 못하는 이유와 같다.
    // => 스태틱 메서드에는 인스턴스 주소를 담은 this라는 변수가 없기 때문이다.
    // => 인스턴스 멤버를 사용하려면 반드시 인스턴스 주소가 있어야 한다. 
    //m2(); // 컴파일 오류!
    
    // 만약 인스턴스 멤버를 사용해야 한다면 다음과 같이 별도로 인스턴스를 생성한 후 사용해야 한다.
    X x = new X();
    x.m2(); // OK!
    
    // 당연히 인스턴스 주소만 있다면 X의 inner 클래스인 B 객체로 생성할 수 있다.
    obj2 = x.new B(); // 실무에서는 이렇게 외부의 인스턴스를 가지로 inner 클래스를 생성하는 
                      // 방식을 거의 사용하지 않는다. 그냥 참고만 하라!
    
  }
 
  void m2() {
    // 인스턴스 멤버에서 static nested class 와 inner 클래스 사용하기
    
    // 스태틱 멤버는 인스턴스 멤버에서도 자유롭게 사용할 수 있다.
    // 즉 다음과 같이 인스턴스 메서드에서 스태틱 메서드를 자유롭게 호출하듯이,
    m1(); // OK!
    
    // 인스턴스 메서드에서 static nested class를 바로 사용할 수 있다.
    A obj = new A();
    
    // 인스턴스 메서드에서 다른 인스턴스 멤버를 자유롭게 사용하듯이
    this.a = 100;
    a = 100; // this 생략 가능
    
    this.m3();
    m3(); // this 생략 가능
    
    B obj2 = this.new B();
    obj2 = new B(); // this 생략 가능
  }
  
  int a;
  void m3() {}
}

public class Test03 {

  public static void main(String[] args) {
    // 다른 클래스에서 중첩 클래스를 사용하기
    // => static nested class 사용
    X.A obj = new X.A();
    
    // => non-static nested class(inner class) 사용
    X.B obj2;
    
    // X의 inner 클래스를 사용하려면 반드시 X의 인스턴스가 있어야 한다.
    //obj2 = new X.B(); // 컴파일 오류!
    //obj2 = new B(); // 컴파일 오류!
    
    X x = new X();
    obj2 = x.new B(); // OK!
  }

}







