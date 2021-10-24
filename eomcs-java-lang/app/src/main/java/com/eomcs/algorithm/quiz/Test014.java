package com.eomcs.algorithm.quiz;

//출처: codefights.com
//한 줄에 버그가 있다. 고쳐라!
/*
Reverse a given integer by rearranging its digits.

Example

numberReverse(123) = 321
numberReverse(120) = 21
[input] integer input

positive integer
[output] integer
 */
public class Test014 {
  static int numberReverse(int input) {

    int reversed = 0;
    while (input >= 0) {
      reversed = reversed * 10 + input % 10;
      input /= 10;
    }
    return reversed;
  }

  public static void main(String[] args) {
    System.out.println(numberReverse(123));

  }

}
