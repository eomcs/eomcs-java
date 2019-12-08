package ch12.c.other;

import ch12.c.My;

public class My2 extends My {
  
  public void m1() {
    My obj1 = new My();
    
    // private
    //obj1.v1 = 100; // 컴파일 오류!
    
    // (default)
    //obj1.v2 = 100; // 컴파일 오류!
    
    // protected
    //obj1.v3 = 100; // 컴파일 오류! obj1이 가리키는 v3이다. 자기가 상속 받은 것이 아니다.
    
    // public 
    obj1.v4 = 100;
    
    // My2가 상속 받은 필드
    //this.v1 = 100; // 컴파일 오류! 상속 받은 필드라도 private은 접근 불가!
    
    //this.v2 = 100; // 컴파일 오류! 상속 받은 필드라도 패키지가 다르면 접근 불가!
    
    this.v3 = 100; // OK! 상속 받은 필드의 경우 서브 클래스에서 접근 가능!
    
    this.v4 = 100; // OK!
  }
}






