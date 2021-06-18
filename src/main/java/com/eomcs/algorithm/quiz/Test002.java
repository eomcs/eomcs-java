package com.eomcs.algorithm.quiz;

// 출처: codefights.com
//
// 숫자 배열에서 각 이웃하는 숫자간의 차가 가장 큰 것을 알아내기
// 예)
// [2, 4, 1, 0] => 3
// 
// 한 줄에 버그가 있다. 고쳐라!

public class Test002 {

  static int maxDiff(int[] values) {
    int answer = 1;
    for (int i = 1; i < values.length; i++) {
      if ((values[i] - values[i - 1]) > answer) {
        answer = Math.abs(values[i] - values[i-1]);
      }
    }
    
    return answer;
  }
  
  public static void main(String[] args) {
    System.out.println(maxDiff(new int[]{2, 4, 1, 0}));

  }

}
