// 클래스의 종류 : non-static 중첩 클래스(nested class) 사용
package com.eomcs.oop.ex11.y;

import java.io.File;
import java.io.FilenameFilter;

public class Exam0131 {
  static int a;
  int b;
  
  // non-static nested class = inner class
  class JavaFilter implements FilenameFilter {
    public boolean accept(File dir, String name) {
      if (name.endsWith(".java")) 
        return true; 
      return false; 
    }
  }

  public static void main(String[] args) throws Exception {
    a = 100; // static 필드
    //b = 100; // 컴파일 오류! 인스턴스 필드 
    
    File dir = new File(".");
    JavaFilter javaFilter; // 레퍼런스를 선언할 때는 상관없다.
    
    // static 메서드에서는 static이 안붙은 인스턴스 멤버를 사용할 수 없다.
    //javaFilter = new JavaFilter(); // 컴파일 오류!
    
    // static 메서드에서 인스턴스 멤버를 사용하려면 
    // 인스턴스를 생성해야만 한다.
    Exam0131 obj = new Exam0131();
    
    // 그리고 이 인스턴스를 가지고 인스턴스 필드/메서드/중첩클래스를 사용할 수 있다.
    javaFilter = obj.new JavaFilter();
    
    String[] names = dir.list(javaFilter);
    for (String name : names) {
      System.out.println(name);
    }
  }

}







