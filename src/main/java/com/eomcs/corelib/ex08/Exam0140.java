// java.util.HashMap - 사용자 정의 데이터 타입을 key로 사용할 경우
package com.eomcs.corelib.ex08;

import java.util.HashMap;

public class Exam0140 {

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

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((major == null) ? 0 : major.hashCode());
      result = prime * result + no;
      return result;
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

    MyKey k6 = new MyKey("컴공", 3);
    // k3와 같은 값을 갖는다.
    // 하지만 인스턴스는 다르다!

    System.out.println(k3 == k6);
    System.out.printf("equals(): %b\n", k3.equals(k6));
    System.out.printf("hashCode(): %d, %d\n", k3.hashCode(), k6.hashCode());
    System.out.println("-----------------------------------");

    // hashCode()의 리턴 값이 같다고 해서 같은 key로 간주하지 않는다.
    System.out.println(map.get(k6));
  }

}


