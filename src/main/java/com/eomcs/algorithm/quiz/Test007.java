package com.eomcs.algorithm.quiz;

// 출처: codefights.com
// 한 줄에 버그가 있다. 고쳐라! 

/* n!(n 팩토리얼) 값을 조사하여 몇 개의 0으로 끝나는 지(trailing zeros) 개수를 리턴하라.
예1) 10!의 값은 두 개의 0으로 끝난다.
예2) 23!은 4개의 값으로 끝난다. (23!의 값은 4바이트를 초과하기 때문에 테스트하지 말아라!)
*/
/* 원문
Given an integer n, find the number of trailing zeros 
in the decimal representation of n! 
(the exclamation mark means factorial).

Example

for n = 10 output should be 2

[input] integer n

a positive integer
[output] integer
*/

public class Test007 {
  static int factorialTrailingZeros(int n) {  
    int result = 0;
    for (int i = 5; i <= n; i += 5) {
      int number = i;
      while (number % 2 == 0) {
        number /= 5;
        result++;
      }
    }
    return result;
  }


  public static void main(String[] args) {
    System.out.println(factorialTrailingZeros(10));

  }

}
