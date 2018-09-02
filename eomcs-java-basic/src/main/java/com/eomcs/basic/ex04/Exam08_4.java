// 형변환 - 부동소수점 변수의 값을 정수 변수에 저장
package com.eomcs.basic.ex04;

public class Exam08_4 {
    public static void main(String[] args) {
        float f = 3.14f;
        double d = 9876.56789;

        // 부동소수점 메모리의 값을 정수 메모리에 저장할 수 없다.
        // 왜? 
        // 무조건 소수점 이하는 제거되기 때문에 
        // 정수 메모리에 저장한다는 것은 위험이 크다.
        // 그래서 자바에서는 부동소수점 값을 정수 메모리에 저장하는 것을 
        // 문법에서 막는다!
        int i = f; // 컴파일 오류!
        long l = d; // 컴파일 오류!
    }
}