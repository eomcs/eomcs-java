// 형변환 - 메모리 크기가 다른 변수의 값을 저장할 때
package com.ohoracs.basic.ex04x;

public class Exam08_1 {
    public static void main(String[] args) {
        byte b = 100;
        short s = 100;
        int i = 100;
        long l = 100;
        char c = 100;

        byte b2;
        short s2;
        int i2;
        long l2;
        char c2;

        // 비록 변수에 들어 있는 값이 작은 값이라 할지라도
        // 큰 메모리에 들어있는 값을 작은 메모리에 넣을 수 없다.
        // => 컴파일 오류!
        
        b2 = s;
        s2 = i;
        i2 = l;
        c2 = s; // 같은 2바이트라도 유효한 값의 범위가 다르다.
                // char(0 ~ 65535), short(-32768 ~ 32767)
        

    }
}
