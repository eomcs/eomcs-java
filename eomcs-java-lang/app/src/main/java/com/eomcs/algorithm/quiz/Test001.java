package com.eomcs.algorithm.quiz;

// 출처: codefights.com
//
// 주어진 숫자에 짝수가 몇 개인지 세어라.
// 예) 
// 1010 => 2
// 123 => 1
//
//한 줄에 버그가 있다. 고쳐라!
public class Test001 {

  static int countEvenNumber(int value) {
    int result = 0;
    int n = value;
    while(n != 0) {
      if ((n % 2) == 0) {
        result++;
      }
      n %= 10;
    }
    return result;
  }

  public static void main(String[] args) {
    System.out.println(countEvenNumber(1238694636));

  }

}
