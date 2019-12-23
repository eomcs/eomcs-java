// 클래스 변수
package com.eomcs.oop.ex03;

public class Exam0130 {
    // 지금 당장 A 클래스 앞에 붙은 static은 고민하지 말라!
    // 이 예제의 목표는 스태틱 변수이다.
    static class A {
        // 클래스 변수 = 스태틱 변수
        // => 클래스를 로딩하는 순간 자동 생성된다. 
        //    클래스 이름으로 사용한다고 해서 "클래스에 소속된 변수", "클래스 변수"라 부른다.
        // => static 이 붙은 변수이기 때문에 "스태틱 변수"라고도 부른다. 
        // => 클래스와 함께 "Method Area" 영역에 존재한다.
        static int v1; 
        static boolean v2; 
    }
    
    public static void main(String[] args) {
        
        // 클래스 변수 사용법
        // 클래스명.스태틱변수명 = 값;
        // 클래스를 사용하는 순간 클래스가 로딩되고, 스태틱 변수는 자동 생성된다.
        A.v1 = 100;
        A.v2 = true;
        
        System.out.printf("%d, %b\n", A.v1, A.v2);
    }
}

// 클래스 변수는 클래스가 로딩될 때 딱 한 번 자동으로 생성되기 때문에
// 각각 구분되는 개별 데이터를 저장할 수 없다.
// 왜? 변수가 한 개이다.







