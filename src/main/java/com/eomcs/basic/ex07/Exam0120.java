// java.util.HashSet vs ArrayList
package com.eomcs.basic.ex07;

import java.util.ArrayList;

// ArrayList는 중복을 허용한다.
public class Exam0120 {
  public static void main(String[] args) {

    ArrayList<String> list = new ArrayList<>();

    list.add("aaa");
    list.add("bbb");
    list.add("ccc");

    // ArrayList는 값을 중복해서 넣을 수 있다.
    list.add("aaa");
    list.add("bbb");

    // null을 넣을 수 있다.
    // 여러 번 넣을 수 있다.
    list.add(null);
    list.add(null);

    System.out.println(list);
  }
}









