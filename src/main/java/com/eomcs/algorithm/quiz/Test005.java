package com.eomcs.algorithm.quiz;

// 출처: codefights.com
//
// 한번에 한 자리의 숫자만 바꿀 때 이전 값과 다르게 만들 수 있는 경우는 몇가지인가?
// 단 맨 앞의 숫자는 0이 될 수 없다.
// 예)
// 10 => 17
//
// 한 줄에 버그가 있다. 고쳐라! 
//
public class Test005 {

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
