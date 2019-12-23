// 클래스의 종류 : non-static 중첩 클래스(nested class) 사용
package com.eomcs.oop.ex11.a;

import java.io.File;
import java.io.FilenameFilter;

public class Exam0130 {
    
    // non-static nested class = inner class
    // => 스태틱이 붙지 않은 중첩 클래스. 결국 인스턴스 멤버이다.
    // => 이 클래스는 인스턴스가 있어야 만 사용할 수 있다.
    // => 보통 인스턴스 멤버를 사용하는 중첩 클래스를 만들 때 inner class로 정의한다.
    // => 당연히 inner 클래스는 인스턴스 메서드에서 주로 사용할 것이다.
    class JavaFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            if (name.endsWith(".java")) 
                return true; 
            return false; 
        }
    }
    
    public void test() {
        File dir = new File(".");
        
        // inner class의 인스턴스를 생성한다.
        JavaFilter javaFilter = new JavaFilter();
        
        // inner class의 인스턴스를 필터로 사용하여 디렉토리 안의 목록을 추출한다.
        String[] names = dir.list(javaFilter);
        for (String name : names) {
            System.out.println(name);
        }
    }
    
    public static void main(String[] args) throws Exception {
        Exam0130 obj = new Exam0130();
        obj.test();
    }

}







