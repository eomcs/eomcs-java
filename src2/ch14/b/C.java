// super 키워드 
package ch14.b;

public class C extends A {
  
  @Override public void m1(int a) {
    System.out.println("C.m1()");
    // 오버라이딩 전의 메서드를 호출하고 싶다면 super 레퍼런스를 사용하라!
    
    super.m1(a); 
  } 
  
  public void m4() {
    System.out.println("C.m4()");
    this.m1(100);
    this.m2("okok", 100);
    super.m2("haha", 200);
  }
  
  public static void main(String[] args) {
    C obj = new C();
    obj.m4();
  }
  
}








