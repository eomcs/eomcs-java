// 비트 이동 연산자 : >>, >>>, << 
package com.eomcs.basic.ex05;

public class Exam04_1 {
    public static void main(String[] args) {
        int i = 1;
        System.out.println(i << 1);
        System.out.println(i << 2);
        System.out.println(i << 3);
        System.out.println(i << 4);
        // 왼쪽으로 이동 후, 
        // 오른 쪽 빈자리 : 0으로 채운다.
        // 왼쪽 경계를 넘어간 비트 : 짜른다.

        i = 0b0000_1011; // 11
        System.out.println(i << 1); // 22 => 00010110
        System.out.println(i << 2); // 44 => 00101100
        System.out.println(i << 3); // 88 => 01011000
    }
}

// 왼쪽 이동
// - 1비트 이동은 곱하기 2 한 것과 같은 효과를 준다.
// 