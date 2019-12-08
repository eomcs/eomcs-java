// 애노테이션 
// => 클래스, 필드, 메서드, 로컬 변수에 붙이는 특별한 주석이다.
// => 다른 주석과 달리 컴파일이나 실행할 때 추출할 수 있다.
// => 애노테이션 문법이 사용되기 전에 일반 주석에 특별한 문법을 포함시켜 사용했던 doclet 이라는 기술이 있었다.
// => 일반 주석과 달리 프로퍼티명=값 형태로 값을 다룰 수 있다.
// => 사용법
//    - 애노테이션 정의 또는 기존에 정의된 애노테이션 사용
//    - 클래스나 인터페이스에 적용
// => 애노테이션이 .class 파일에 있는 것을 확인하라!
//
package ch28.a;

@MyAnnotation // 클래스 선언에 붙일 수 있다.
//@MyAnnotation // 중복으로 붙일 수는 없다.
public class Test01 {
  
  @MyAnnotation // 필드에 붙일 수 있다.
  static int a;
  
  @MyAnnotation int b; // 애노테이션을 붙인 후에 바로 코드를 작성해도 된다.
  
  @MyAnnotation // 메서드 선언에 붙일 수 있다.
  void m1(
      @MyAnnotation 
      int p1, // 파라미터(로컬변수)에 붙일 수 있다. 
      @MyAnnotation String p2) {
    
    @MyAnnotation int local; // 로컬변수 선언에 붙일 수 있다.
    
    //@MyAnnotation System.out.println("okok"); // 그러나 다른 일반 문장에는 붙일 수 없다.
    
    //@MyAnnotation // 다른 일반 문장에는 붙일 수 없다.
    for (int i = 0; i < 100; i++) {
      @MyAnnotation int a; // 로컬 변수 선언에 붙일 수 있다.
    }
  }
  
  @MyAnnotation  // static, non-static 상관없이 메서드 선언에 붙일 수 있다.
  static void m2() {
    
  }
  
}






