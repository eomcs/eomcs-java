// java.util.HashMap - 사용자 정의 데이터 타입을 key로 사용할 경우
package com.eomcs.corelib.ex07;

import java.util.HashMap;

public class Exam0120 {

  static class MyKey {
    String major;
    int no;

    public MyKey(String major, int no) {
      this.major = major;
      this.no = no;
    }

    @Override
    public String toString() {
      return "MyKey [major=" + major + ", no=" + no + "]";
    }
  }

  public static void main(String[] args) {
    Member v1 = new Member("홍길동", 20);
    Member v2 = new Member("임꺽정", 30);
    Member v3 = new Member("유관순", 16);
    Member v4 = new Member("안중근", 30);
    Member v5 = new Member("윤봉길", 25);

    MyKey k1 = new MyKey("컴공", 1);
    MyKey k2 = new MyKey("컴공", 2);
    MyKey k3 = new MyKey("컴공", 3);
    MyKey k4 = new MyKey("컴공", 4);
    MyKey k5 = new MyKey("컴공", 5);

    HashMap map = new HashMap();

    map.put(k1, v1);
    map.put(k2, v2);
    map.put(k3, v3);
    map.put(k4, v4);
    map.put(k5, v5);

    System.out.println(map.get(k1));
    System.out.println(map.get(k2));
    System.out.println(map.get(k3));
    System.out.println(map.get(k4));
    System.out.println(map.get(k5));

    // 같은 값을 갖지만 인스턴스가 다른 key
    MyKey k6 = new MyKey("컴공", 3); // k3와 같은 값을 갖는다.
    // 하지만 인스턴스는 다르다!

    System.out.println(map.get(k6));
    // k6로 값을 찾으면 없다고 나온다.
    // 이유?
    // => HashMap에서 값을 찾을 때 key가 같아야 한다.
    //    key 객체가 다르더라도 hashCode()의 리턴 값이 같고,
    //    equals()의 리턴 값이 true이면 두 개의 key는 같다고 간주한다.
    // => 그러나 우리가 사용한 MyKey 클래스는 hashCode()와 equals()를 
    //    오버라이딩 하지 않았기 때문에,
    //    값이 같더라도 인스턴스가 다르면 hashCode()의 리턴 값이 다르게 나온다.
    //    equals() 또한 false를 리턴한다.
    // 해결책?
    // => key로 사용할 클래스는 항상 hashCode()와 equals()를 오버라이딩 하여 
    //    같은 값을 갖는 객체에 대해 같은 해시값을 리턴하게 해야 한다.
  }

}







