package com.eomcs.basic.ex04;

//# 변수에 값 저장 - 부동소수점 변수의 값을 정수 변수에 저장
//
public class Exam921 {
  public static void main(String[] args) {
    float f = 3.14f;
    double d = 9876.56789;

    // 부동소수점 메모리의 값을 정수 메모리에 저장할 수 없다.
    // 왜? 
    // - 정수 메모리는 소수점 이하의 값을 저장할 수 없기 때문에
    //   자바에서는 부동소수점 값을 정수 메모리에 저장하는 것을 
    //   문법에서 막는다!
    int i = f; // 컴파일 오류!
    long l = d; // 컴파일 오류!
  }
}