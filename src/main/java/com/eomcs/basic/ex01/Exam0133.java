// Object 클래스의 메서드를 오버라이딩 하기 - equals()
package com.eomcs.basic.ex01;

public class Exam0133 {

  static class Member /*extends Object */{
    String name;
    int age;

    public Member(String name, int age) {
      this.name = name;
      this.age = age;
    }

    // Object로부터 상속 받은 equals() 메서드를 
    // Member 클래스의 역할에 맞게 재정의해보자! => "오버라이딩"
    @Override
    public boolean equals(Object obj) {
      if (!(obj.getClass() == Member.class))
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
    Member m3 = new Member("홍길동", 21);

    System.out.println(m1 == m2);
    System.out.println(m1 == m3);

    System.out.println(m1.equals(m2));
    System.out.println(m1.equals(m3));
  }
}

// 결론!
// => Object로부터 상속 받은 기본 메서드인 equals()는 
//    같은 인스턴스인지를 비교하는 메서드이다.
// => 인스턴스의 데이터가 같은지를 비교하고 싶다면, 
//    이 메서드를 재정의(오버라이딩) 해야 한다.
// => 오버라이딩의 예:
//    String, 
//    랩퍼 클래스(Byte,Short,Integer,Long,
//    Float,Double,Boolean,Character)
// 







