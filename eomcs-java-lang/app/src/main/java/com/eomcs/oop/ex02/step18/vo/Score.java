package com.eomcs.oop.ex02.step18.vo;

public class Score {

  public String name;
  public int kor;
  public int eng;
  public int math;
  public int sum;
  public float aver;

  public void init(String name, int kor, int eng, int math) {
    this.name = name;
    this.kor = kor;
    this.eng = eng;
    this.math = math;
  }

  public void compute() {
    this.sum = this.kor + this.eng + this.math;
    this.aver = this.sum / 3f;
  }
}
