package com.eomcs.algorithm.quiz;
//copyright by codefights.com
//한 줄에 버그가 있다. 고쳐라!
/*
Find the largest number from the given three integers

[input] integer a

[input] integer b

[input] integer c

[output] integer

max of a,b and c
 */
public class Test022 {
  static int myMaxOfThree(int a, int b, int c) {
    if (a > b) {
      if (a > c) {
        return a;
      }
      return b;
    }
    if (b > c) {
      return b;
    }
    return c;
  }
  public static void main(String[] args) {
    System.out.println( myMaxOfThree(7, 5, 8)); //결과는 8이어야 한다.

  }

}
