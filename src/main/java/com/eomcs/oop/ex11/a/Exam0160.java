// 클래스의 종류 : anonymous class II
package com.eomcs.oop.ex11.a;

import java.io.File;
import java.io.FilenameFilter;

public class Exam0160 {
    
    public static void main(String[] args) throws Exception {
        
        File dir = new File(".");
        
        // anonymous class
        // => 인스턴스를 여러 곳에서 쓸 게 아니라면 필요한 그 장소 바로 정의하라!
        // => 익명 클래스가 사용될 곳에 정의되어 있기 때문에 소스 코드를 해석하기가 편하다.
        //    만약 다른 별도의 장소에 클래스가 정의되어 있다면, 그 클래스가 무엇을 하는지 
        //    파악하기 위해서는 그 장소가 이동해야 한다.
        // => 그런데 다음과 같이 익명 클래스가 사용될 장소에 바로 클래스 정의가 있기 때문에
        //    그 클래스가 무엇을 하는지 소스 코드를 파악하기 편하다!
        String[] names = dir.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                if (name.endsWith(".java")) 
                    return true; 
                return false; 
            }
        });
        
        for (String name : names) {
            System.out.println(name);
        }
    }

}







