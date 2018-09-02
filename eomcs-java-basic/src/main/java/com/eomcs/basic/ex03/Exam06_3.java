// 문자 변수 - UTF-16(Unicode 2) 코드
package com.eomcs.basic.ex03;

public class Exam06_3 {
    public static void main(String[] args) {
        int a = 0xac00;
        System.out.println(a); // a 메모리의 종류가 int이기 때문에
                               // 그냥 10진수 정수 값을 출력한다.
        
        // println()에게 정수 값을 문자 코드라고 알려주면 
        // 문자를 출력한다.
        System.out.println((char)a);

        for (int i = 0; i < 100; i++) {
            // 10자 출력하고 줄 바꿈한다.
            if ((i % 10) == 0) System.out.println();
            
            // 주어진 수를 문자 코드라고 알려준다.
            System.out.print((char)('가' + i));
        }
    }
}

// 결론!
// - char 메모리에는 0에서 65535까지의 정수 값을 저장한다.
// - 즉 UTF-16에 정의된 문자에 대한 2바이트 코드 값을 저장하는 것이다.
// - 메모리의 문자를 직접 저장할 방법은 없다.
//   반드시 2진수로 변환하여 저장해야 된다.
// - 그것은 색깔, 빛의 세기, 소리 등에 동일하게 해당된다.
// - 즉 메모리에 저장하는 것은 2진수 값이다.
// - 결국 '가'의 의미는?
//   작은 따옴표는 아주 특별한 명령어이다.
//   작은 따옴표 안에 주어진 문자의 코드 값을 리턴하는 일을 한다.
//   '가' == 44032 값과 같다
//
