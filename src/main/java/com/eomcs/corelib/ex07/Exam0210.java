// java.util.HashMap - key 목록을 사용하여 맵에서 값을 꺼내기
package com.eomcs.corelib.ex07;

import java.util.HashMap;
import java.util.Iterator;

public class Exam0210 {
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

    // => HashMap에서 key 목록을 꺼내기
    java.util.Set keys = map.keySet(); // key 객체들이 들어있는 집합(컬렉션)을 리턴한다.

    // key가 들어있는 Set에서 값을 꺼내는 방법
    // 1) Iterator 사용 
    Iterator 키를꺼내주는객체 = keys.iterator();
    while (키를꺼내주는객체.hasNext()) {
      Object key = 키를꺼내주는객체.next();
      System.out.println(map.get(key)); // key를 사용하여 값을 꺼낸다.
    }
    System.out.println("---------------------");

    // 2) 배열을 사용
    Object[] keyArray = keys.toArray();
    for (Object key : keyArray) {
      System.out.println(map.get(key)); // key를 사용하여 값을 꺼낸다.
    }
    System.out.println("---------------------");

    // 3) 만약 컬렉션 객체가 java.util.Collection 규칙을 따라 만든 클래스라면 
    //    for(:) 반복문을 바로 사용할 수 있다.
    for (Object key : keys/* 배열 또는 Collection 객체 */) {
      System.out.println(map.get(key));
    }
    System.out.println("---------------------");

  }

}







