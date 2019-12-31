// java.util.HashMap - key/value 한쌍으로 묶어 꺼내기 
package com.eomcs.corelib.ex07;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Exam0220 {
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

    Set entrySet = map.entrySet(); // key/value가 한쌍으로 묶여있는 객체들이 들어있다.

    for (Object obj : entrySet) {
      // Set 컬렉션에 들어있는 개체는 원래 Entry 객체이다.
      // Entry 객체에서 key와 값을 꺼내려면 
      // 원래의 타입으로 형변환 해야 한다. 
      Entry entry = (Entry) obj;
      System.out.printf("%s ===> %s\n", 
          entry.getKey(), entry.getValue());
    }

  }

}







