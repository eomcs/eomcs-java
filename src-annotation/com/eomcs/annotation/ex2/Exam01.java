// 애노테이션 확인
package com.eomcs.annotation.ex2;

public class Exam01 {

    public static void main(String[] args) {
        // 클래스 정보 객체로부터 애노테이션 정보 추출
        Class clazz = MyClass.class;
        
        // => 유지정책 : CLASS 
        MyAnnotation obj = (MyAnnotation)clazz.getAnnotation(MyAnnotation.class);
        if (obj == null) {
            System.out.println("MyAnnotation을 추출할 수 없습니다!");
        }
        
        // => 유지정책 : SOURCE
        MyAnnotation2 obj2 = (MyAnnotation2)clazz.getAnnotation(MyAnnotation2.class);
        if (obj2 == null) {
            System.out.println("MyAnnotation2를 추출할 수 없습니다!");
        }
        
        // => 유지정책 : RUNTIME
        MyAnnotation3 obj3 = (MyAnnotation3)clazz.getAnnotation(MyAnnotation3.class);
        if (obj3 == null) {
            System.out.println("MyAnnotation3를 추출할 수 없습니다!");
        } else {
            // 값을 꺼낼 때는 메서드 호출하듯이 꺼내면 된다.
            System.out.println("MyAnnotation3.value=" + obj3.value());
        }
        
    }
}




