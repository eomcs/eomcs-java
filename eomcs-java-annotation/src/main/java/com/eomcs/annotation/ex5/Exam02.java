// 애노테이션 프로퍼티 값 추출 - 배열 값 추출
package com.eomcs.annotation.ex5;

public class Exam02 {

    public static void main(String[] args) {
        Class clazz = MyClass2.class;
        MyAnnotation2 obj = (MyAnnotation2)clazz.getAnnotation(MyAnnotation2.class);
        
        System.out.println(obj.v1()[0]);
        System.out.println(obj.v2()[0]);
        System.out.println(obj.v3()[0]);
        
    }
}




