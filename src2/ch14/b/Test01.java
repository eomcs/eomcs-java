// 오버라이딩과 this/super
package ch14.b;

public class Test01 {

  public static void main(String[] args) {
    X2 obj = new X2();
    obj.test();
    System.out.println("--------------------");
    X3 obj2 = new X3();
    obj2.test();
    System.out.println("--------------------");
    X4 obj3 = new X4();
    obj3.test();
    System.out.println("--------------------");
    X5 obj4 = new X5();
    obj4.test(); 
  }

}
