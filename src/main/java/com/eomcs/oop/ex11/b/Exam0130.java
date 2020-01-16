// 클래스의 종류 : non-static 중첩 클래스(nested class) 사용
package com.eomcs.oop.ex11.b;

import java.io.File;
import java.io.FilenameFilter;

public class Exam0130 {

  // non-static nested class = inner class
  // => 스태틱이 붙지 않은 중첩 클래스. 결국 인스턴스 멤버이다.
  // => 이 클래스는 인스턴스가 있어야만 사용할 수 있다.
  // => 보통 인스턴스 멤버를 사용하는 중첩 클래스를 만들 때 inner class로 정의한다.
  // => 당연히 inner 클래스는 인스턴스 메서드에서 주로 사용할 것이다.
  // 
  
  // inner class = non-static nested class
  // => 바깥 클래스의 인스턴스를 사용하는 경우 inner class로 정의하라.
  // => 인스턴스 필드를 사용하는 메서드에 대해 인스턴스 메서드로 선언하는 것과 같다.
  //
  class JavaFilter implements FilenameFilter {
    public boolean accept(File dir, String name) {
      if (name.endsWith(".java")) 
        return true; 
      return false; 
    }
  }

  public static void test1() {
    // JavaFilter는 non-static nested class 이기 때문에 
    // 인스턴스를 생성할 때 반드시 바깥 클래스의 인스턴스가 필요하다.
    // 따라서 이 메서드처럼 static 메서드에서 해당 클래스를 사용하려 한다면 
    // 오류가 발생할 것이다.
    //
    JavaFilter javaFilter; // 레퍼런스를 선언할 때는 상관없다.
    
    //javaFilter = new JavaFilter(); // 컴파일 오류
  }
  
  public void test2() {
    File dir = new File(".");
    
    // 인스턴스 메서드에서는 non-static nested class의 인스턴스를 생성할 수 있다.
    // => 바깥 클래스의 인스턴스 주소를 앞쪽에 지정한 후 해당 클래스의 인스턴스를 생성하라.
    //
    JavaFilter javaFilter; // 레퍼런스를 선언할 때는 상관없다.
    
    javaFilter = this.new JavaFilter(); // OK!
    
    // 다음과 같이 this를 생략해도 된다.
    //javaFilter = new JavaFilter(); // OK!

    // inner class의 인스턴스를 필터로 사용하여 디렉토리 안의 목록을 추출한다.
    String[] names = dir.list(javaFilter);
    for (String name : names) {
      System.out.println(name);
    }
  }

  public static void main(String[] args) throws Exception {
    Exam0130 obj = new Exam0130();
    obj.test2();
  }

}







