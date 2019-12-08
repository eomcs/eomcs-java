package com.eomcs.basic.ex04;

//# 배열 - 배열 메모리의 기본 값
//
public class Exam63 {
  public static void main(String[] args) {
    
    // 배열 메모리는 생성될 때 기본 값으로 초기화 된다.
    // - 정수 배열 : 0
    // - 부동소수점 배열: 0.0
    // - 문자 배열 : 0 (문자 없음을 의미)
    // - 논리 배열 : false
    //
    int[] arr1 = new int[5];
    float[] arr2 = new float[5];
    char[] arr3 = new char[5];
    boolean[] arr4 = new boolean[5];
    
    System.out.println(arr1[0]);
    System.out.println(arr2[0]);
    System.out.println(arr3[0]);
    System.out.println(arr4[0]);
  }
}