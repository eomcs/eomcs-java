package com.eomcs.basic.ex04;

//# 배열 - 배열 메모리의 자동 초기화 
//
public class Exam612 {
  public static void main(String[] args) {
    
    // 일반 변수는 값을 저장하지 않고 사용할 수 없다.
    int v;
    //System.out.println(v); // 컴파일 오류!
    
    // new 명령으로 확보된 메모리는 메모리의 종류에 상관없이 기본 값으로 자동 초기화 된다.
    // 따라서 배열 메모리 또한 생성되는 순간 기본 값으로 자동 초기화 된다.
    // - byte, short, int, long : 0
    // - float, double : 0.0
    // - boolean : false 
    // - char : '\u0000'
    // - 주소 변수 : null
    //
    int[] arr1 = new int[5];

    System.out.println(arr1[0]);
    System.out.println(arr1[1]);
    System.out.println(arr1[2]);
    System.out.println(arr1[3]);
    System.out.println(arr1[4]);
  }
}