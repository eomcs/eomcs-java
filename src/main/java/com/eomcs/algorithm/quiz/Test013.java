package com.eomcs.algorithm.quiz;

//출처: codefights.com
//한 줄에 버그가 있다. 고쳐라!
/*
For given n and k find the sum of all multiples of k 
that are not greater than n.

Example

sumOfMultiples(7, 2) = 2 + 4 + 6 = 12

[input] integer n

positive integer
[input] integer k

positive integer
[output] integer
 */
public class Test013 {
  static int sumOfMultiples(int n, int k) {

    int result = 0;

    for (int i = 1; i <= n; i++) {
      if (i % k == 0) {
        result++;
      }
    }
    return result;
  }
  public static void main(String[] args) {
    System.out.println(sumOfMultiples(7, 2));

  }

}
