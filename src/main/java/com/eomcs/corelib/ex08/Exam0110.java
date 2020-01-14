// java.util.HashMap - 값을 저장할 때 key을 사용하여 저장한다.
package com.eomcs.corelib.ex08;

import java.util.HashMap;

public class Exam0110 {
  public static void main(String[] args) {
    Member v1 = new Member("홍길동", 20);
    Member v2 = new Member("임꺽정", 30);
    Member v3 = new Member("유관순", 16);
    Member v4 = new Member("안중근", 20);
    Member v5 = new Member("유관순", 16);

    HashMap map = new HashMap();

    // 값을 저장할 때 key로 사용할 객체를 지정한다.
    // 보통 key 객체로 String 객체를 많이 사용한다.
    // => 물론 어떤 객체라도 key로 사용할 수 있다.
    map.put("s01", v1);
    map.put("s02", v2);
    map.put("s03", v3);
    map.put("s04", v4);

    // 이전에 저장할 때 사용한 같은 키로 다른 값을 저장하면 기존 값을 덮어쓴다.
    map.put("s04", v5);

    System.out.println(map.get("s01"));
    System.out.println(map.get("s02"));
    System.out.println(map.get("s03"));
    System.out.println(map.get("s04"));

    // 존재하지 않는 key를 지정하면 null을 리턴한다. 
    System.out.println(map.get("s05"));
  }

}







