// HashMap과 Set 키 목록
package com.eomcs.basic.ex08;

import java.util.HashMap;
import java.util.Set;

public class Exam0310 {
  public static void main(String[] args) {

    HashMap<String,Member> map = new HashMap<>();
    map.put("s01", new Member("홍길동", 20));
    map.put("s02", new Member("임꺽정", 30));
    map.put("s03", new Member("유관순", 16));
    map.put("s04", new Member("안중근", 20));
    map.put("s05", new Member("윤봉길", 30));

    Set<String> keys = map.keySet();

    // 키목록을 얻은 후에 값을 삭제한다.
    map.remove("s01");
    map.remove("s02");
    map.remove("s03");

    // Set 객체를 통해 key 를 꺼낼 때,
    // 그 순간의 HashSet에 있는 key를 꺼낸다.
    // 즉 keySet()을 호출할 때 모든 key를 미리 목록을 만들어 리턴하지 않는다.
    for (Object key : keys) {
      System.out.println(key);
    }
  }

}


