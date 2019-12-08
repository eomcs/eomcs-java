// 클래스 필드와 인스턴스 필드 
package ch10;

class My1 {
  // 인스턴스에 상관없이 공유하는 값이라면 스태틱 필드로 선언하라!
  static int a;
  
  // 인스턴스 별로 개별적으로 관리되어야 할 값이라면 인스턴스 필드로 선언하라!
  int b;
}

public class Test01 {
  public static void main(String[] args) {
    My1 obj1;
    obj1 = new My1();
    My1 obj2 = new My1();
    My1 obj3 = new My1();
    
    // 스태틱 필드는 모든 인스턴스가 공유하는 변수이다.
    My1.a = 100;
    
    // 인스턴스의 레퍼런스로 스태틱 필드를 사용할 수 있다.
    obj1.a = 200; // = My1.a
    obj2.a = 300; // = My1.a
    obj3.a = 400; // = My1.a
    
    System.out.println(My1.a);
    
    // 인스턴스 필드는 인스턴스 마다 존재하는 변수이다.
    // 즉 인스턴스 마다 개별적으로 다뤄야 할 값을 저장한다.
    obj1.b = 100;
    obj2.b = 200;
    obj3.b = 300;
    
    System.out.println(obj1.b);
    System.out.println(obj2.b);
    System.out.println(obj3.b);
  }
}
















