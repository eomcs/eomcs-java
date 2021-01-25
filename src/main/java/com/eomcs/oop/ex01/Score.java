package com.eomcs.oop.ex01;

//# 패키지 클래스 
// - 별도의 파일로 정의한 클래스를 "패키지 클래스"라 부른다.
// - 즉 패키지에 직접 소속된 클래스라는 의미다.
// - 이전에 만든 Score 클래스는 main() {} 블록 안에 정의하였다.
//   main() {} 블록 안에 정의한 클래스는 
//   main() {} 블록 안에서만 사용할 수 있다.
// - 이렇게 별도의 파일로 정의한 클래스는 
//   다른 클래스에서도 사용할 수 있다. 
public class Score {
  public String name;
  public int kor;
  public int eng;
  public int math;
  public int sum;
  public float aver;
}