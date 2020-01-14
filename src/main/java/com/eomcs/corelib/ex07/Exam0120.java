// java.util.HashSet vs ArrayList
package com.eomcs.corelib.ex07;

import java.util.ArrayList;
import java.util.HashSet;

// ArrayList는 중복을 허용한다.
public class Exam0120 {
  public static void main(String[] args) {
    String v1 = new String("aaa");
    String v2 = new String("bbb");
    String v3 = new String("ccc");
    String v4 = new String("ddd");
    String v5 = new String("ccc");

    ArrayList list = new ArrayList();
    list.add(v1);
    list.add(v2);
    list.add(v3);
    list.add(v4);
    list.add(v5); 

    print(list);
  }

  static void print(ArrayList set) {
    Object[] values = set.toArray();
    for (Object value : values) {
      System.out.print(value + ", ");
    }
    System.out.println();
  }
}









