// 부동소수점의 리터럴 
package com.eomcs.basic.ex02;

public class Exam06_1 {
    public static void main(String[] args) {
        
        System.out.println(3.14);

        // exponential 기호를 사용하기
        // => 숫자e지수 또는 숫자E지수 
        System.out.println(0.0314e2); // 0.0314 * 10의2승 
        System.out.println(0.314e1); // 0.314 * 10의1승
        System.out.println(31.4e-1); // 31.4 * 10의-1승 
        System.out.println(314e-2); // 314 * 10의-2승 
        // => e 기호를 사용하면 소수점의 위치를 조정할 수 있다.
        //    소수점의 위치를 움직인다고 해서 "부동소수점"이라 부른다. 
        
    }
}