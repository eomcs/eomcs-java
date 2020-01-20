// java.util.HashMap - value 목록만 꺼내기
package com.eomcs.corelib.ex08;

import java.util.Collection;
import java.util.HashMap;

public class Exam0230 {
  public static void main(String[] args) {
    Member v1 = new Member("홍길동", 20);
    Member v2 = new Member("임꺽정", 30);
    Member v3 = new Member("유관순", 16);
    Member v4 = new Member("안중근", 20);
    Member v5 = new Member("윤봉길", 25);

    HashMap map = new HashMap();
    map.put("s01", v1);
    map.put("s02", v2);
    map.put("s03", v3);
    map.put("s04", v4);
    map.put("s05", v5);

    // key 객체는 중복되어서는 안되기 때문에
    // key 객체 목록은 Set 에 담아서 리턴한다.
    // value 객체는 중복 저장될 수 있기 때문에
    // value 객체 목록은 Collection 에 담아서 리턴한다.
    //
    Collection values = map.values();
    for (Object value : values) {
      System.out.println(value);
    }

  }

}


