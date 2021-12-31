package com.eomcs.oop.ex02.util;

public class Calculator2 {
  int result = 0;

  public void plus(int a) {
    this.result = this.result + a;
  }

  public void minus(int a) {
    this.result = this.result - a;
  }

  public void multiple(int a) {
    this.result = this.result * a;
  }

  public void divide(int a) {
    this.result = this.result / a;
  }

  public int getResult() {
    return this.result;
  }
}