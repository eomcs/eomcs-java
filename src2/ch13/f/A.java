package ch13.f;

// 수퍼 클래스를 지정하지 않으면, 무조건 자동으로 java.lang.Object를 상속 받는다.
//
public class A /*extends Object*/ {
  private int v1 = 100;
  
  public A() { 
    // 생성자를 정의할 반드시 수퍼 클래스의 어떤 생성자를 호출할 것인지 지정해야 한다.
    //    문법:   super(파라미터값, 파라미터값, ...);
    // 만약 지정하지 않는다면 무조건 수퍼 클래스의 기본 생성자를 호출한다.
    //    super();
    // 수퍼 클래스의 생성자를 호출할 때는 반드시 첫 번째 문장으로 와야 한다.
    //
    //System.out.println("A.A()"); // 컴파일 오류!
                                   //수퍼 클래스의 생성자를 호출하는 문장 보다 먼저 올 수 없다.
    super(); // 생략하면 수퍼 클래스의 기본 생성자를 호출하는 코드가 자동으로 추가된다.
             // A 클래스의 수퍼클래스? Object 이다.
    
    System.out.println("A.A()");
  }
  
  public void m1() {
    System.out.printf("A.v1 = %d\n", this.v1);
  }
}
