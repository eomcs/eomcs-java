package com.eomcs.lang.ex04;

public class Test01 {
  public static void main(String[] args) {
    int[] a = new int[3];
    int[] b = a;

    a[1] = 100;

    System.out.println(b[1]);

    b[1] = 200;
    System.out.println(a[1]);
  }
}
