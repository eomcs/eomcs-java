// String - chatAt()과 codePointAt()
package com.eomcs.basic.ex02;

public class Exam1100 {
  public static void main(String[] args) {
    char[] chars = Character.toChars(128149);
    String str = String.valueOf(chars);
    System.out.println(str);
    System.out.println(str.length());
    System.out.println(str.codePointCount(0, str.length()));
    System.out.println(chars.length);
    System.out.printf("%x %x\n", (int)chars[0], (int)chars[1]);

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
//     - 110110xx xxxxxxxx
//   - low surrogate: U+DC00 ~ U+DFFF(1024개)
//     - 110111xx xxxxxxxx
// - 예) 💕(128149 = 0x01f495 = 0001 11110100 10010101
//   - 110110xx xxxxxxxx 110111xx xxxxxxxx
//   - 11011000 00111101 11011100 10010101
//   d83d dc95 =
//


