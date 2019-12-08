package ch13.e;

public class B extends A {
  private int v2 = 200;
  
  { // 인스턴스 블록
    v2 = 210;
    System.out.println("B.{}");
  }
  
  public B() { // 생성자
    v2 = 220;
    System.out.println("B.B()");
  }
  
  public void m2() {
    System.out.printf("B.v2 = %d\n", this.v2);
  }
}
