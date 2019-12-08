package ch12.c;

public class Test01 {

  public static void main(String[] args) {
    My obj1 = new My();
    
    // private 접근 불가!
    //obj1.v1 = 100; // 컴파일 오류!

    // (default) 접근 가능 
    obj1.v2 = 100; // OK! My 클래스와 Test01 클래스는 같은 패키지에 소속되어 있다.
    
    // protected 접근 가능
    obj1.v3 = 100; // OK! My 클래스와 Test01 클래스는 같은 패키지에 소속되어 있다.
    
    // public 접근 가능
    obj1.v4 = 100; // OK! 완전 공개이다.
  }

}
