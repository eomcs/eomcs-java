package ch13.f;

public class X {
  private int v1 = 100;
  
  public X(int value) { 
    //super(); // 생략하면 수퍼 클래스의 기본 생성자를 호출하는 코드가 자동으로 추가된다.
             // A 클래스의 수퍼클래스? Object 이다.
    
    this.v1 = value;
    System.out.println("A.A()");
  }
  
  public void m1() {
    System.out.printf("A.v1 = %d\n", this.v1);
  }
}
