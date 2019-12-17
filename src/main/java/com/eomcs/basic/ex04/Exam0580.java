package com.eomcs.basic.ex04;

//# 배열 - 배열 인스턴스와 가비지
//
public class Exam0580 {
  public static void main(String[] args) {

    int[] arr1;
    arr1 = new int[5];
    arr1[0] = 100;
    
    // 레퍼런스에 다른 배열 인스턴스의 주소를 담을 수 있다.
    arr1 = new int[] {200, 200, 200};
    // 주의!
    // - arr1이 보관하고 있던 주소는 잃어버렸기 때문에 이전 배열 인스턴스는 사용할 수 없다.
    // - 이렇게 주소를 잃어버려 사용할 수 없는 인스턴스(메모리)를 '가비지(garbage)'라 부른다.
    //
    
    // 새 배열 인스턴스의 값 출력하기
    System.out.println(arr1[0]);
  }
}