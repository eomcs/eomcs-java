package com.eomcs.algorithm.quiz;

public class Test {

  static int countWaysToChangeDigit(int value) {
    int answer = 0;  
    while (value > 0) {    
      answer *= 9 - value % 10;
      value /= 10;
    }
    return answer;
  }



  public static void main(String[] args) {
    System.out.println(countWaysToChangeDigit(10));

  }

}
