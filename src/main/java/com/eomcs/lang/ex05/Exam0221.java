// 관계 연산자 : 부동소수점 비교 
package com.eomcs.lang.ex05;

public class Exam0221 {
  public static void main(String[] args) {
    float f1 = 0.1f;
    float f2 = 0.1f;
    
    System.out.println(f1 * f2 == 0.01f); // false
    
    // 이유?
    System.out.println(f1 * f2); // 0.010000001 결과 값 뒤에 0.000000001 오차 발생!
    
    // 해결책?
    // => 오차를 제거한 후 비교
    // => 다만 계산 결과를 절대값으로 만든 후에 오차 이하의 값인지 비교하라!
    float r = f1 * f2 - 0.01f;
    System.out.println(Math.abs(r) <= Float.POSITIVE_INFINITY);
  }
}
