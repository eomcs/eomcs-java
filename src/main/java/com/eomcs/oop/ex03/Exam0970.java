// 종합 정리 - 생성자에서 다른 생성자 호출하기: this()
package com.eomcs.oop.ex03;

public class Exam0970 {

  static class Monitor {

    int bright; // 밝기 (0% ~ 100%)
    int contrast = 50; // 명암 (0% ~ 100%)
    int widthRes; // 해상도 너비
    int heightRes = 1080; // 해상도 높이

    Monitor() {
      // 다른 생성자를 호출할 수 있다.
      // 단 첫 문장으로 와야 한다.
      this(50, 50, 2560, 1200);
      System.out.println("Monitor()");
    }

    Monitor(int bright, int contrast) {
      // 다른 생성자를 호출할 수 있다.
      // 단 첫 문장으로 와야 한다.
      this(bright, contrast, 2560, 1200);
      System.out.println("Monitor(int, int)");
    }

    Monitor(int bright, int contrast, int widthRes, int heightRes) {
      this.bright = bright;
      this.contrast = contrast;
      this.widthRes = widthRes;
      this.heightRes = heightRes;
      System.out.println("Monitor(int, int, int, int)");
    }

    void display() {
      System.out.println("----------------------------------");
      System.out.printf("밝기(%d)\n", this.bright);
      System.out.printf("명암(%d)\n", this.contrast);
      System.out.printf("해상도(%d x %d)\n", this.widthRes, this.heightRes);
      System.out.println("----------------------------------");
    }
  }

  public static void main(String[] args) {
    // 인스턴스 생성

    Monitor m1 = new Monitor();
    System.out.println("--------------------------------");

    Monitor m2 = new Monitor(60, 60);
    System.out.println("--------------------------------");

    Monitor m3 = new Monitor(70, 70, 1920, 1080);
    System.out.println("--------------------------------");

    m1.display();
    m2.display();
    m3.display();
  }
}


