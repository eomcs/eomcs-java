// Iterator 의 사용
package com.eomcs.basic.ex07;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

public class Exam0240 {
  public static void main(String[] args) {
    String s1 = new String("aaa");
    String s2 = new String("bbb");
    String s3 = new String("ccc");
    String s4 = new String("ddd");
    String s5 = new String("eee");

    ArrayBlockingQueue queue = new ArrayBlockingQueue(100);
    queue.offer(s1);
    queue.offer(s2);
    queue.offer(s3);
    queue.offer(s4);
    queue.offer(s5);

    Iterator 컬렉션에서값을꺼내주는객체 = queue.iterator();
    while (컬렉션에서값을꺼내주는객체.hasNext()) {
      System.out.print(컬렉션에서값을꺼내주는객체.next() + ", ");
    }
    System.out.println();

  }
}
