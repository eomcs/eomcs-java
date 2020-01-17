// non-static nested class = inner class 응용 III
package com.eomcs.oop.ex11.a;

import java.util.ArrayList;
import java.util.Iterator;

public class Exam0242 {
  public static void main(final String[] args) {
    final ArrayList<String> m1 = new ArrayList<>();
    m1.add("aaa.mp3");
    m1.add("bbb.mp3");
    m1.add("bbb.mp3");

    final Iterator<String> i1 = m1.iterator();
    final Iterator<String> i2 = m1.iterator();

    while (i1.hasNext()) {
      System.out.println(i1.next());
    }
    System.out.println("--------------------");

    while (i2.hasNext()) {
      System.out.println(i2.next());
    }
    System.out.println("--------------------");
  }
}

