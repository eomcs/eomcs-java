// 산술 연산자 : 연산의 결과 타입  
package com.eomcs.basic.ex05;

public class Exam01_4 {
    public static void main(String[] args) {
        int i = 5;
        int j = 2;
        float r = i / j; // int와 int의 연산 결과는 항상 int이다.
                         // 따라서 r 변수에 넣기 전에 
                         // 이미 결과는 정수 2가 된다.
                         // 정수 2를 float 변수에 넣으면 
                         // 출력할 때 2.0이 된다.
        System.out.println(r);

        // 해결책!
        // - 변수에 들어 있는 값을 다른 타입으로 바꿔라.
        //   "형변환(type conversion=type casting)"하라!
        r = (float)i / (float)j; // float / float = float
                                 // i / j의 값은 2.5가 되고
                                 // r에 저장되는 것은 2.5이다.
        System.out.println(r);                         

        // int와 int의 연산 결과는 int이다.
        // 다른 타입이 될 수 없다.
        // 0111 1111 1111 1111 1111 1111 1111 1111 = Integer.MAX_VALUE
        int x = Integer.MAX_VALUE; // 0x7fffffff = 약 +21억
        int y = Integer.MAX_VALUE; // 0x7fffffff = 약 +21억
        int r1 = x + y; // 0x7fffffff + 0x7fffffff = 0xfffffffe = -2
        long r2 = x + y;  // 0x7fffffff + 0x7fffffff = 0xfffffffe = -2
        System.out.println(r1); // int(4byte) + int(4byte) = int(4byte)
        System.out.println(r2); // int(4byte) + int(4byte) = int(4byte)
        // r2의 출력 결과를 보면 42억이 출력되는 것이 아니라 -2가 출력된다.
        // 이유?
        // int 와 int의 연산 결과는 피연산자와 같은 4바이트 int가 된다.
        // 그래서 8바이트 long 변수에 저장하기 전에 
        // 이미 그 결과는 int 값으로 -2가 되기 때문에 
        // long 변수의 값이 -2가 된다.

        float f1 = 987.6543f;
        float f2 = 1.111111f;
        float r3 = f1 + f2; 
        double r4 = f1 + f2;
        System.out.println(r3); // 988.7654
        // 출력 이유?
        // 987.6543 + 1.111111 = 988.7654(11)
        // float과 float의 연산 결과는 float이다.
        // 그래서 메모리 크기를 넘어가는 뒤의 11은 
        // 짤린다.
        // 짤린 값이 r3와 r4에 저장된다.

        System.out.println(r4); // 988.765380859375
        // 출력이유?
        // float 값(988.7654...)을 
        // double 변수에 저장할 때 정확하게 저장되지 않는다.
        // IEEE 754의 이진수 변환 문제때문이다.

        double d1 = 987.6543;
        double d2 = 1.111111;
        double r5 = d1 + d2; // = 988.765411
        System.out.println(r5);
    }
}

// 정리!
// - int와 int의 연산 결과는 int이다.
// - float과 float의 연산 결과는 float이다.
// - 즉 연산 결과는 피연산자의 타입과 같다.
// - 그래서 두 값을 계산했을 때 결과 메모리를 초과할 경우
//   값이 짤릴 수 있다.
//   주의하라!
// - 코드를 작성할 때 피연산자의 계산 결과가 피연산자의 메모리 크기를
//   벗어날 가능성이 있다면,
//   처음부터 피연산자의 값을 더 큰 메모리에 담아서 연산을 수행하라!
//   
//
// 형변환(type casting=type conversion)?
// - 변수나 리터럴을 다른 타입의 값을 바꾸는 것이다.
// - 주의!
//   원래 변수의 타입을 바꾸는 것이 아니다.
//   내부적으로는 변수에 들어 있는 값을 꺼내 
//   지정된 타입의 임시 메모리를 만들어 저장한다.
//
// 결론!
// 1) 자바의 최소 연산 단위는 int이다.
//    따라서 int 보다 작은 크기의 메모리 값을 다룰 때는
//    내부적으로 int로 자동 형변환을 수행한 다음에 연산을 수행한다.
//    내부적으로 자동 형변환하는 것을 
//    "암시적 형변환(implicit type conversion)"이라 부른다.
// => byte + byte = int
// => short + short = int
// => byte + short = int
// 
// 2) 연산 결과는 항상 피연산자의 타입과 같다.
// => int + int = int
// => long + long = long
// => float + float = float
// => double + double = double
//
// 3) 다른 타입과 연산을 수행할 때는 
//    내부적으로 같은 타입으로 맞춘 다음에 실행한다.
//    Exam01_5.java를 보라!