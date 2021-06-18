package com.eomcs.algorithm.quiz;

//출처: codefights.com
//한 줄에 버그가 있다. 고쳐라! 
/*
Given first two elements of an arithmetic progression, find its n-th element.

[input] integer element1

1st element of the arithmetic progression
[input] integer element2

2nd element of the arithmetic progression
[input] integer n

positive integer
[output] integer

n-th element of the arithmetic progression
 */
public class Test009 {
  static int arithmeticProgression(int element1, int element2, int n) {
    return element1 + (element2 - element1) * n;
  }
  
  public static void main(String[] args) {
    System.out.println(arithmeticProgression(2, 5, 5)); // 2, 5, 8, 11, 14

  }

}
