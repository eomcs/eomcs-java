// indexOf()에서 객체를 찾을 때 equals()로 비교하여 찾는다.
package com.eomcs.corelib.ex03;

import java.util.ArrayList;

public class Exam0330 {
  // equals()를 오버라이딩 하지 않았다.
  static class Member {
    String name;
    int age;

    public Member(String name, int age) {
      this.name = name;
      this.age = age;
    }

    @Override
    public String toString() {
      return String.format("[%s,%d]", this.name, this.age);
    }
  }
  public static void main(String[] args) {
    Member s1 = new Member("홍길동", 20);
    Member s2 = new Member("임꺽정", 30);
    Member s3 = new Member("유관순", 16);
    Member s4 = new Member("임꺽정", 30);

    ArrayList list = new ArrayList();
    list.add(s1);
    list.add(s2);
    list.add(s3);
    print(list);

    // Member 클래스는 equals()를 오버라이딩 하지 않았다.
    // 따라서 같은 값을 갖더라도 인스턴스가 다르면 equals()의 검사 결과도 false가 될 것이다.
    System.out.println(list.indexOf(s4)); // -1
  }

  static void print(ArrayList list) {
    for (int i = 0; i < list.size(); i++) {
      System.out.print(list.get(i) + ", ");
    }
    System.out.println();
  }
}   






