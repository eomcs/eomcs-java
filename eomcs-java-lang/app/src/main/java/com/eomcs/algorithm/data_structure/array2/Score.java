package com.eomcs.algorithm.data_structure.array2;

public class Score {
  String name;
  int kor;
  int eng;
  int math;
  int sum;
  float aver;

  public Score(String name, int kor, int eng, int math) {
    this.name = name;
    this.kor = kor;
    this.eng = eng;
    this.math = math;
    this.compute();
  }

  private void compute() {
    this.sum = this.kor + this.eng + this.math;
    this.aver = this.sum / 3f;
  }
}
