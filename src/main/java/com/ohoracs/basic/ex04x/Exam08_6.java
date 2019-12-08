// 형변환 - 명시적 형변환 II
package com.ohoracs.basic.ex04x;

public class Exam08_6 {
    public static void main(String[] args) {
        byte b = 100;
        short s = 100;
        int i = 100;
        long l = 100;

        // 큰 메모리의 값을 작은 메모리에 저장하는 경우
        // => 작은 메모리에 들어갈 수 있는 값인 경우에 형변환을 해야 한다.
        byte b2 = (byte)s; // 명시적 형변환
        System.out.println(b2);

        b2 = (byte)i; // 명시적 형변환
        System.out.println(b2);

        b2 = (byte)l; // 명시적 형변환
        System.out.println(b2);

        // => 작은 메모리에 들어갈 값 보다 큰 값을
        //    명시적 형변환을 수행하여 값을 넣으려 하면 
        //    원래의 값이 짤려서 들어간다.
        int i2 = 0b0000_0000_0000_0000_0000_0001_0010_1100; // = 300(10진수)
        b2 = (byte) i2; // 
        System.out.println(b2); // 0b0010_1100
        // 특별한 경우 큰 값을 작은 메모리에 넣는 것은 아무런 의미가 없다.
        // 해서는 안된다.
        // 다만,
        // 큰 메모리의 값을 바이트 단위로 쪼개고 싶을 때 
        // 이 방식을 응용한다.
    }
}
