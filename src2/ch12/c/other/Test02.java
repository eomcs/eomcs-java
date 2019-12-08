package ch12.c.other;

import ch12.c.My;

public class Test02 {

  public static void main(String[] args) {
    My obj1 = new My();
    
    // private
    //obj1.v1 = 100; // 컴파일 오류!

    // protected
    //obj1.v2 = 100; // 컴파일 오류! My 클래스와 Test02 클래스는 서로 다른 패키지 소속이다.
    
    // protected
    //obj1.v3 = 100; // 컴파일 오류!
    
    // public 접근 가능
    obj1.v4 = 100; // OK! 완전 공개이다.
  }

}
