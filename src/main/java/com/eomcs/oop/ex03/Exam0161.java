// 클래스 변수의 응용
//
package com.eomcs.oop.ex03;

public class Exam0161 {

  static class Member {
    
    // 클래스 변수의 활용 예: 상수 변수를 선언할 때
    // - 모든 인스턴스가 같은 값을 가질 경우,
    //   글래스 변수로 만드는 것이 바람직하다.
    // - 값을 저장하는 것이 아닌 조회하는 용도로만 사용하는 변수라면
    //   대문자로 선언하여 상수임을 표현하라!
    // - 조회 용도로만 사용하기로 했으면 final로 값을 변경되는 것을 막아라!
    // - 보통 조회용으로 사용하는 변수는 공개해도 되기 때문에 public으로 선언한다!
    public static final int GUEST = 0;
    public static final int MEMBER = 1;
    public static final int MANAGER = 2;

    String id;
    String password;
    int type; // 0: 손님, 1: 회원, 2: 관리자
  }

  public static void main(String[] args) {
    
    // 이전 예제의 문제점을 해결하기 위해 나온 것이 상수이다.
    // - 즉 의미있는 이름으로 변수를 만들어 놓고 그 변수에 숫자를 저장한다.
    // - 그리고 그 값을 사용할 때는 변수를 통해 사용한다.
    
    // 다음은 상수를 사용한 예이다.
    Member m4 = new Member();
    m4.id = "aaa";
    m4.password = "1111";
    m4.type = Member.GUEST;

    Member m5 = new Member();
    m5.id = "bbb";
    m5.password = "1111";
    m5.type = Member.MANAGER;

    Member m6 = new Member();
    m6.id = "ccc";
    m6.password = "1111";
    m6.type = Member.MEMBER;
  }
}








