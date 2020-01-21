// 메서드 레퍼런스 - 생상자 레퍼런스 활용
package com.eomcs.oop.ex12;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.function.Supplier;

public class Exam0750 {

  static Collection prepareNames(Supplier factory, String... names) {
    Collection list = (Collection) factory.get();
    for (String name : names) {
      list.add(name);
    }
    return list;
  }

  static void print(Iterator i) {
    while (i.hasNext()) {
      System.out.print(i.next() + ",");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    // '람다'는 새로 메서드를 구현해야 하지만,
    // '메서드 레퍼런스'는 기존 클래스의 메서드를 재활용 할 수 있다.
    //

    // 생성자 레퍼런스
    // 문법:
    // => 클래스명::new
    //
    Collection c1 = prepareNames(ArrayList::new, "홍길동", "임꺽정", "유관순", "임꺽정");
    print(c1.iterator());

    System.out.println("------------------------");

    Collection c2 = prepareNames(HashSet::new, "홍길동", "임꺽정", "유관순", "임꺽정");
    print(c2.iterator());
  }

}


