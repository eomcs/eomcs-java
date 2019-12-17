package com.eomcs.basic.ex07;

//# 메서드 : 인스턴스와 Heap 메모리 영역
//
public class Exam0430 {

  static MyObject getMyObject() {
    // Exam03_3.java 에 정의된 MyObject 클래스 사용
    MyObject ref = new MyObject();
    ref.a = 100;
    ref.b = 200;

    return ref;
  }

  public static void main(String[] args) {
    MyObject ref;
    ref = getMyObject();
    System.out.println(ref.a);
    System.out.println(ref.b);
  }
}

// 1) main() 호출
//    => JVM Stack: args, ref 변수 생성
// 2) getMyObject() 호출
//    => JVM Stack: ref 변수 생성
//    => Method Area: MyObject 클래스를 로딩
//    => Heap: MyObject 설계도에 따라 인스턴스 생성
// 3) getMyObject() 호출 끝
//    => JVM Stack: getMyObject() 관련 메모리(ref 변수) 제거
//    => MyObject의 인스턴스의 주소 리턴
// 4) main() 호출 끝
//    => JVM Stack: main() 관련 메모리 제거 
// 5) JVM 종료
//    => JVM이 사용한 모든 메모리(Method Area, JVM Stack, Heap 등)를 OS 반납.