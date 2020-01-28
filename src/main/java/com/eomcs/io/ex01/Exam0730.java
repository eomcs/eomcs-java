// 활용 - 지정한 폴더에서 .class 파일만 찾아 출력하라.
package com.eomcs.io.ex01;

import java.io.File;
import java.io.FileFilter;

public class Exam0730 {

  public static void main(String[] args) throws Exception {

    File dir = new File("bin/main");
    System.out.println(dir.getCanonicalPath());

    printList(dir);
  }

  static void printList(File dir) {

    File[] files = dir.listFiles(new FileFilter() {
      @Override
      public boolean accept(File pathname) {
        if (pathname.isHidden()) {
          return false;
        }

        if (pathname.getName().contains("$")) {
          return false;
        }

        if (pathname.isDirectory()
            || (pathname.isFile() && pathname.getName().endsWith(".class"))) {
          return true;
        }
        return false;
      }
    });

    for (File file : files) {
      if (file.isDirectory()) {
        printList(file);
      } else {
        System.out.printf("%s\n", file.getName());
      }
    }
  }

  static void printIndent(int level) {
    for (int i = 0; i < level; i++) {
      System.out.print("  ");
    }
  }

}


