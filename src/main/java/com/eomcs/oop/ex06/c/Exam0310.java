// 오버라이딩(overriding) - 오버라이딩할 때 접근 범위 조정하기
package com.eomcs.oop.ex06.c;


//멤버의 접근 범위
//private      : 같은 클래스
//(default)    : 같은 클래스 + 같은 패키지
//protected    : 같은 클래스 + 같은 패키지 + 서브 클래스
//public       : 모두
//

class C {
  //private 접근 범위:
  //=> 현재 클래스
  private void m1() {}

  //(default) 접근 범위:
  //=> 현재 클래스 + 같은 패키지 소속 클래스
  void m2() {}

  //protected 접근 범위:
  //=> 현재 클래스 + 같은 패키지 소속 클래스 + 서브 클래스
  protected void m3() {}

  //public 접근 범위:
  //=> 모두
  public void m4() {}
}

class C2 extends C {

  // 다음은 C의 메서드를 오버라이딩 한 게 아니다!
  // 왜?
  // - C의 m1()은 private이기 때문에 오직 C에서만 사용할 수 있다.
  // - C2에서 접근할 수 없다. 접근할 수 없으니, 오버라이딩은 불가능하다!
  //
  //  @Override
  //  private void m1() {} // 컴파일 오류!

  // 어? 강사님! @Override 빼니까 m1() 정의할 수 있는데요?
  // => 이건 오버라이딩이 아니라 C2에 새로 메서드가 추가된 것이다.
  // => 오버라이딩이라면 @Override 애노테이션을 붙였을 때 오류가 나지 말아야 한다!
  //
  private void m1() {}
  // 결론! 접근 권한이 없는 메서드는 오버라이딩 불가!

  // 다음은 전형적인 오버라이딩의 예이다.
  @Override void m2() {} // OK
  @Override protected void m3() {} // OK
  @Override public void m4() {} // OK
}


class C3 extends C {

  // 오버라이딩 메서드의 접근 범위를 확대하는 것은 괜찮다.
  // => private 은 오버라이딩 자체가 불가능하기 때문에 접근 범위를 확대할 수 없다.
  //@Override public m() {}

  @Override protected void m2() {} // default 보다 접근 범위를 확대했기 때문에 OK!
  @Override public void m3() {} // protected 보다 접근 범위를 확대!

}

class C4 extends C {

  // 오버라이딩 할 때(메서드를 재정의 할 때)
  // => 원본 보다 접근 범위를 좁힐 수는 없다.
  //
  //  @Override private void m2() {} // (default) ==> private : 컴파일 오류!
  //  @Override void m3() {} // protected ==> (default) : 컴파일 오류!
  //  @Override protected void m4() {} // public ==> protected : 컴파일 오류!

  // 결론!
  // 수퍼 클래스의 메서드를 자식 클래스가 재정의할 때
  // 접근 범위를 확대할 순 있지만, 좁힐 수는 없다!

}

public class Exam0310 {
  public static void main(String[] args) {
    // C, C2, C3, C4 클래스의 주석을 확인하라!
  }
}








