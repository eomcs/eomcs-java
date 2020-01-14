// Iterator 의 사용
package com.eomcs.corelib.ex07;

import java.util.Iterator;
import java.util.Stack;

public class Exam0230 {
  public static void main(String[] args) {
    String s1 = new String("aaa");
    String s2 = new String("bbb");
    String s3 = new String("ccc");
    String s4 = new String("ddd");
    String s5 = new String("eee");

    Stack stack = new Stack();
    stack.add(s1);
    stack.add(s2);
    stack.add(s3);
    stack.add(s4);
    stack.add(s5);

    Iterator 컬렉션에서값을꺼내주는객체 = stack.iterator();
    while (컬렉션에서값을꺼내주는객체.hasNext()) {
      System.out.print(컬렉션에서값을꺼내주는객체.next() + ", ");
    }
    System.out.println();

  }
}
