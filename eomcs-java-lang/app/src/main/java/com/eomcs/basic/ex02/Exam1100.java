// String - chatAt()과 codePointAt()
package com.eomcs.basic.ex02;

public class Exam1100 {
  public static void main(String[] args) throws Exception {
    char[] chars = Character.toChars(128149);
    String str = String.valueOf(chars);
    System.out.printf("문자열: %s\n", str);

    System.out.println("UTF-16(surrogate pair) --------------------");
    System.out.printf("char[].length: %d\n", chars.length);
    System.out.printf("char[]: %x %x\n", (int)chars[0], (int)chars[1]);
    System.out.printf("String.length(): %d\n", str.length());
    System.out.printf("String.codePointCount(): %d\n", str.codePointCount(0, str.length()));

    System.out.println("UTF-8 --------------------");
    byte[] bytes = str.getBytes("UTF-8");
    System.out.printf("byte[].length: %d\n", bytes.length);
    System.out.printf("byte[]: %02x %02x %02x %02x\n",
        bytes[0] & 0xff,
        bytes[1] & 0xff,
        bytes[2] & 0xff,
        bytes[3] & 0xff);
  }
}

// ASCII(American Standard Code for Information Interchange)
// - 0 ~ 31: 출력할 수 없는 제어코드
// - 32 ~ 127: 출력할 수 있는 문자코드
// - 128 ~ 255: 확장 아스키 코드
//
// 유니코드(Unicode)
// - code point = codepoint = code position
//   - 특정 문자에 부여한 정수 값이다.
//   - code point의 최대 크기는 공식적으로 21비트(0x10FFFF)로 제한되었다.
//   - 항상 맨앞에 'U+'를 붙여서 16진수로 표현한다.
//   - 최소 2바이트에서 최대 3바이트로 표현: U+0000 ~ U+10FFFF
//   - 예1) A : U+0041
//   - 예2) 가: U+AC00
//   - 예3) 💕: U+1F494
// - 자바에서 유니코드를 다루는 방법
//   - char(16비트) 타입 메모리를 사용하여 UTF-16 BE 으로 인코딩 한다.
// - 유니코드 현재 버전(15.0)
//   - 65536(0x100000) ~ 1,114,111(0x10FFFF) 사이의 정수 값을 문자에 부여함
//   - 자바는 UTF-32를 지원하지 않는다.
//   - 대부분의 문자는 UTF-16으로 인코딩 할 수 있다.
//   - 메모리 효율성을 위해 UTF-16으로 표현할 수 없는 경우에만
//     "Surrogate Pair" 방식으로 인코딩 한다.
// Surrogate Pair(유니코드 대리 쌍)
// - 대리 쌍: code point가 65536(0x010000) ~ 1,114,111(0x10FFFF) 사이의 값을 4바이트로 표현
//   - 1,048,576 개의 문자
// - 16비트 두 쌍을 이용하여 인코딩 한다.
//   - high surrogate + low surrogate = 4바이트
//   - high surrogate: U+D800 ~ U+DBFF(1024개)
//     - 110110uu uuyyyyyy
//     - uuuu = 원래 값(zzzz) - 1 => code point - 65536
//   - low surrogate: U+DC00 ~ U+DFFF(1024개)
//     - 110111yy xxxxxxxx
// - 예) 💕(128149 = 0x01f495 = 0001 11110100 10010101)
//   - code point가 0 ~ 65535 까지의 값은 UTF-16으로 저장하고
//     그 이후의 값을 4바이트 대리쌍으로 저장하는 것이니까
//     code point 65536을 0으로 시작하여 대리쌍으로 저장한다.
//   - 따라서 code point 128149(0x1f495)을 저장할 때는 먼저 65536을 빼야 한다.
//     128149(0x1f495) - 65536(0x10000) = 62613(0xf495)
//   - 2진수 계산법
//     원래 값: zzzz yyyyyyyy xxxxxxxx
//     계산 값: uuuu yyyyyyyy xxxxxxxx (uuuu = zzzz - 1 => 즉 65536을 뺀다)
//     대리 쌍: 110110uu uuyyyyyy 110111yy xxxxxxxx
//   - 계산 예) 💕(128149 = 0x1f495)
//     원래 값: 0001 11110100 10010101
//     계산 값: 0000 11110100 10010101
//     대리 쌍: 11011000 00111101 11011100 10010101 (d8 3d dc 95)
// UTF-8
// -  U+0000  ~    U+007F: 0xxxxxxx
// -  U+0080  ~    U+07FF: 110xxxxx 10xxxxxx
// -  U+0800  ~    U+FFFF: 1110xxxx 10xxxxxx 10xxxxxx
// - U+10000  ~  U+10FFFF: 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
// - 예) 💕(128149 = 0x01f495 = 0001 11110100 10010101)
//   원래 값: 0001 11110100 10010101
//     UTF-8: 11110000 10011111 10010010 10010101 (f0 9f 92 95)
// Modified UTF-8
// - 자바 언어에서 사용
// - U+0000 : 0xc080(11000000 1000000)
//   - 전통적으로 문자열을 다루는 함수는 null을 문자열의 끝으로 인식한다.
//     이런 함수들이 문자열을 다루는데 문제가 없도록 0000 대신에 C080을 사용한다.
// -  U+0000  ~    U+007F: 0xxxxxxx
// -  U+0080  ~    U+07FF: 110xxxxx 10xxxxxx
// -  U+0800  ~    U+FFFF: 1110xxxx 10xxxxxx 10xxxxxx
// - U+10000  ~  U+10FFFF: 11101101 1010xxxx 10xxxxxx 11101101 1011xxxx 10xxxxxx
// - 예) 💕(128149 = 0x01f495 = 0001 11110100 10010101)
//          원래 값: 0001 11110100 10010101
//   Modified UTF-8: 11101101 10100001 10111101 11101101 10110010 10010101 (ed a1 bd ed b2 95)


