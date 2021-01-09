// java.util.Map - 사용자 정의 데이터 타입을 key로 사용할 경우 IV
package com.eomcs.basic.ex08;

import java.util.HashMap;

public class Exam0150 {

  public static void main(String[] args) {

    class MyKey {
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

      @Override
      public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((major == null) ? 0 : major.hashCode());
        result = prime * result + no;
        return result;
      }

      @Override
      public boolean equals(Object obj) {
        if (this == obj)
          return true;
        if (obj == null)
          return false;
        if (getClass() != obj.getClass())
          return false;
        MyKey other = (MyKey) obj;
        if (major == null) {
          if (other.major != null)
            return false;
        } else if (!major.equals(other.major))
          return false;
        if (no != other.no)
          return false;
        return true;
      }
    }

    MyKey k1 = new MyKey("컴공", 1);
    MyKey k2 = new MyKey("컴공", 2);
    MyKey k3 = new MyKey("컴공", 3);
    MyKey k4 = new MyKey("컴공", 4);
    MyKey k5 = new MyKey("컴공", 5);

    HashMap<MyKey, Member> map = new HashMap<>();

    map.put(k1, new Member("홍길동", 20));
    map.put(k2, new Member("임꺽정", 30));
    map.put(k3, new Member("유관순", 16));
    map.put(k4, new Member("안중근", 30));
    map.put(k5, new Member("윤봉길", 25));

    System.out.println(map.get(k1));
    System.out.println(map.get(k2));
    System.out.println(map.get(k3));
    System.out.println(map.get(k4));
    System.out.println(map.get(k5));

    System.out.println("-----------------------------");


    // k3 와 같은 값을 갖는 키를 생성한다.
    MyKey k6 = new MyKey("컴공", 3);

    System.out.println(k3 == k6);
    System.out.printf("equals(): %b\n", k3.equals(k6));
    System.out.printf("hashCode(): %d, %d\n", k3.hashCode(), k6.hashCode());
    System.out.println("-----------------------------------");

    System.out.println(map.get(k6));
    // k3와 k6는 인스턴스가 다르더라도
    // hashCode()의 리턴 값이 같고, equals()의 결과가 true이기 때문에
    // 같은 key로 간주한다.
    //
    // 결론!
    // - HashMap의 key 객체로 사용할 클래스는 반드시 hashCode()와 equals()를
    //   오버라이딩 하여 같은 값을 갖는 경우 같은 해시 값을 리턴하게 하라!
    // - 대부분 현업에서는 그냥 String을 key로 사용한다.
    //   또는 Wrapper 클래스인 Integer를 사용하기도 한다.
  }

}


