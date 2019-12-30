// 클래스 변수의 응용
//
package com.eomcs.oop.ex03;

public class Exam0160 {

  static class Member {
    // => 값을 저장하는 것이 아닌 조회하는 용도로만 사용하는 변수라면
    //    대문자로 선언하여 상수임을 표현하라!
    // => 조회 용도로만 사용하기로 했으면 문법적으로 값을 변경하는 것을 막아라!
    // => 모든 인스턴스가 다음의 변수에 대해 같은 값을 가질 경우,
    //    글래스 변수로 만드는 것이 바람직하다.
    // => 보통 조회용으로 사용하는 변수는 공개해도 되기 때문에 public으로 선언한다!
    public static final int GUEST = 0;
    public static final int MEMBER = 1;
    public static final int MANAGER = 2;

    String id;
    String password;
    int type; // 0: 손님, 1: 회원, 2: 관리자
  }

  public static void main(String[] args) {
    Member m1 = new Member();
    m1.id = "aaa";
    m1.password = "1111";
    m1.type = 0;

    Member m2 = new Member();
    m2.id = "bbb";
    m2.password = "1111";
    m2.type = 2;

    Member m3 = new Member();
    m3.id = "ccc";
    m3.password = "1111";
    m3.type = 1;

    // 회원의 타입을 지정할 때 숫자를 이용하였다.
    // 그런데 소스 코드를 작성한 후 시간이 오래 지나면 숫자의 의미를 기억하기가 쉽지 않다.
    // 소스 코드를 작성한 사람도 숫자의 의미를 기억하기가 쉽지 않은데,
    // 남의 소스 코드를 유지보수하는 사람은 더더욱 숫자의 의미를 파악하기란 어렵다.
    // 주석이나 개발 문서를 자세히 살펴보지 않는 이상에는 숫자의 의미를 파악하기 쉽지 않다.
    //
    // "이런 경우를 방지하고자 나온 문법이 상수이다!"
    // => 즉 의미있는 이름으로 변수를 만들어 놓고 그 변수에 숫자를 저장한다.
    // => 그리고 그 값을 사용할 때는 변수를 통해 사용한다.
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








