package com.eomcs.advanced.oop.exam11;

import java.io.File;

public class App {

  public static void main(String[] args) {
    System.out.println(Math.PI);
    System.out.println(Math.abs(-100));
    File dir = new File(System.getProperty("user.dir"));

    File[] files = dir.listFiles(new MyFileFilter());

    if (files == null) {
      System.out.println("디렉토리가 아니거나 I/O 오류가 발생했습니다.");
      return;
    }

    for (File file : files) {
      if (file.isDirectory()) {
        System.out.println("디렉토리");
      } else {
        System.out.println("파일");
      }
      System.out.println("[파일] " + file.getName());
    }
  }
}
