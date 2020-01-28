// 활용 - 클래스 파일 이름을 출력할 때 패키지 이름을 포함하라.
package com.eomcs.io.ex01;

import java.io.File;
import java.io.FileFilter;

public class Exam0731 {

  public static void main(String[] args) throws Exception {

    File dir = new File("bin/main");
    System.out.println(dir.getCanonicalPath());

    printList(dir, "");
  }

  static void printList(File dir, String packageName) {

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

    if (packageName.length() > 0) {
      packageName += ".";
    }
    for (File file : files) {
      if (file.isDirectory()) {
        printList(file, packageName + file.getName());
      } else {
        System.out.println(packageName + file.getName().replace(".class", ""));
      }
    }
  }

  static void printIndent(int level) {
    for (int i = 0; i < level; i++) {
      System.out.print("  ");
    }
  }

}


