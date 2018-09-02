// 정수의 리터럴 
package com.eomcs.basic.ex02;

public class Exam05_1 {
    public static void main(String[] args) {
        // 10진수
        System.out.println(100);

        // 8진수
        // - 0으로 시작한다.
        System.out.println(0144);

        // 2진수
        // - 0b 또는 0B 로 시작한다.
        System.out.println(0b1100100);

        // 16진수
        // - 0x 또는 0X 로 시작한다.
        System.out.println(0x64);
    }
}

// 10진수
// - 일반적으로 값을 알려주고 싶을 때 사용한다.
// 8진수
// - 코드에서 거의 사용하지 않는다.
// 2진수
// - 메모리 상태를 알려주고 싶을 때 사용한다.
// 16진수
// - 2진수로 표기하면 너무 길어지기 때문에 
//   주소 16진수로 표기한다.
//
// 결론!
// - 코드를 작성할 때 주로 10진수로 표기한다.
// - 간혹 메모리 상태에 집중하라고 강조하고 싶을 때 16진수로 표기한다.