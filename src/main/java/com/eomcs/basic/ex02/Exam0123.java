// String - String.equals() 처럼 동작하게 만들기
package com.eomcs.basic.ex02;

public class Exam0123 {

  static class Member {
    String name;
    int age;

    public Member(String name, int age) {
      this.name = name;
      this.age = age;
    }

    // String의 equals()처럼 내용이 같은지를 비교하도록 만들고 싶다면,
    // Object에서 상속 받은 메서드를 오버라이딩 하라.
    @Override
    public boolean equals(Object obj) {
      if (obj == null)
        return false;

      if (obj.getClass() != Member.class)
        return false;

      Member other = (Member) obj;

      if (!this.name.equals(other.name))
        return false;

      if (this.age != other.age)
        return false;

      return true;
    }
  }

  public static void main(String[] args) {

    Member m1 = new Member("홍길동", 20);
    Member m2 = new Member("홍길동", 20);

    // 인스턴스는 다르지만,
    System.out.println(m1 == m2); // false

    // 인스턴스의 내용물이 같기 때문에 결과는?
    System.out.println(m1.equals(m2)); // true
  }
}


