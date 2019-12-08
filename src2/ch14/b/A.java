package ch14.b;

public class A {
  
  void m1(int a) {
    System.out.println("A.m1()");
  }
  
  protected void m2(String a, int b) {
    System.out.println("A.m2()");
  }
  
  public Object m3() {
    System.out.println("A.m3()");
    return null;
  }
 
}
