// 논리값 변수 - 유효한 값 범위
package com.eomcs.basic.ex03;

public class Exam07_1 {
    public static void main(String[] args) {
        boolean b1, b2;
        b1 = true; // 실제 메모리에는 1을 저장한다.
        b2 = false; // 실제 메모리에는 0을 저장한다.

        System.out.println(b1);
        System.out.println(b2);

        // true, false 대신에 1, 0 정수 값을 저장할 수 없다.
        //b1 = 1; // 컴파일 오류!
        //b2 = 0; // 컴파일 오류!
    }
}
