package com.eomcs.basic.ch03.solution;

//# 논리값 리터럴 - 크기

public class Exam51 {
    public static void main(String[] args) {
        
        // 다음과 같이 개별적으로 논리 값을 표현할 때는
        // 내부적으로 각 논리 값을 4바이트 메모리에 보관한다.
        System.out.println(true);
        System.out.println(false);

        // 그러나 다음과 같이 여러 개의 논리 값을 배열에 보관할 때는
        // 내부적으로 각 논리 값을 1바이트 메모리에 보관한다.
        boolean[] arr = {true, true, false, true, false, false}; 
        System.out.println(arr[0]);
        System.out.println(arr[1]); 
        System.out.println(arr[2]); 

    }
}