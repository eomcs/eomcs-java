// java.util.HashSet 클래스 사용 - 중복저장 불가 테스트
package com.eomcs.corelib.ex06;

import java.util.HashSet;

// HashSet의 특징
// 1) 값을 중복 저장하지 않는다.
//    => Set은 집합을 의미한다.
//    => 집합에서는 중복값을 허용하지 않는다.
//    => 값의 중복 여부는 hashCode()의 리턴 값이 같고, equals()의 검사 결과가 true일 때 
//       같은 값으로 취급한다.
//    => 즉 중복된 값을 저장하고 싶지 않을 때 HashSet을 사용한다.
// 2) 값을 순서대로 저장하지 않는다.
//    => 값 객체의 hashCode()의 리턴 값으로 저장 위치를 계산하기 때문에 
//       add() 한 순서대로 저장되지 않는다.
//    => 그래서 값을 index를 이용하여 꺼낼 수 없다.
public class Exam0110 {
  public static void main(String[] args) {
    String v1 = new String("aaa");
    String v2 = new String("bbb");
    String v3 = new String("ccc");
    String v4 = new String("ddd");
    String v5 = new String("ccc");

    HashSet set = new HashSet();
    set.add(v1);
    set.add(v2);
    set.add(v3);
    set.add(v4);
    set.add(v5); 
    // v5와 v3는 다른 인스턴스이다. 하지만 두 객체에 들어있는 문자열은 같다. 
    // String 클래스는 인스턴스가 다르더라도 값이 같으면,
    // hashCode()의 리턴 값이 같도록 오버라이딩 하였다.
    // 또한 equals()의 리턴 값도 true가 되도록 오버라이딩 하였다.

    // HashSet은 값의 중복을 검사할 때 인스턴스의 주소를 비교하지 않고
    // hashCode()의 리턴 값과 equals()의 리턴 값으로 판단한다.
    // 따라서 v5는 비록 v3와 다른 인스턴스이지만, 
    // hashCode()의 리턴 값이 같고 두 객체에 대해 equals()를 호출하면 
    // true가 리턴되기 때문에 같은 값으로 간주하여 중복 저장하지 않는다.
    // 결론! v5는 추가되지 않는다.

    print(set);
  }

  static void print(HashSet set) {
    Object[] values = set.toArray();
    for (Object value : values) {
      System.out.print(value + ", ");
    }
    System.out.println();
  }
}









