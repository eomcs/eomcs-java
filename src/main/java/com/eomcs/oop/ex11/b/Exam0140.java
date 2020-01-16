// 클래스의 종류 : local class
package com.eomcs.oop.ex11.b;

import java.io.File;
import java.io.FilenameFilter;

public class Exam0140 {

  public static void main(String[] args) throws Exception {

    // local class
    // => 특정 메서드 안에서만 사용할 클래스라면 그 메서드 안에 클래스를 정의하라!
    // => 메서드 안에 정의된 클래스를 "로컬 클래스(local class)"라 부른다.
    //
    class JavaFilter implements FilenameFilter {
      public boolean accept(File dir, String name) {
        if (name.endsWith(".java")) 
          return true; 
        return false; 
      }
    }

    File dir = new File(".");

    // local class의 인스턴스를 생성한다.
    JavaFilter javaFilter = new JavaFilter();

    // local class의 인스턴스를 필터로 사용하여 디렉토리 안의 목록을 추출한다.
    String[] names = dir.list(javaFilter);
    for (String name : names) {
      System.out.println(name);
    }
  }

}







