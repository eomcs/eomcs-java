package com.eomcs.algorithm.quiz;
//copyright by codefights.com
//한 줄에 버그가 있다. 고쳐라!
/*
Given a number and a range, find the largest integer 
within the given range that's divisible by the given number.

[input] integer left

the left bound of the given range
[input] integer right

the right bound of the given range, left <= right
[input] integer divisor

a non-zero integer
[output] integer

maximal integer in the range [left, right] which is divisible 
by divisor or -1 if there in no such number
 */

public class Test021 {

  static int maxDivisor(int left, int right, int divisor) {

    for (int i = right; i <= left; i--) {
      if (i % divisor == 0) {
        return i;
      }
    }
    return -1;
  }
  
  public static void main(String[] args) {
    System.out.println( maxDivisor(2, 17, 4));

  }

}
