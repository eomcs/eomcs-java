// 논리 연산자: &, |
package ch04;

public class Test11 {
  public static void main(String[] args) {
    // 피연산자가 boolean 타입일 때는 &&, ||와 동일하게 수행한다.
    // & (AND) 논리 연산자
    // => 피 연산자 모두 true 일 때만 결과가 true 된다.
    System.out.println(true & true);  // true
    System.out.println(true & false); // false
    
    // | (OR) 논리 연산자
    // => 둘 중 한 개라도 true 이면 결과가 true이다.
    System.out.println(true | false); // true
    System.out.println(false | false); // false
    
    // 피연산자가 정수일 때는 비트 연산을 수행한다.
    // 비트연산? 비트와 비트끼리 and, or 연산을 수행한다.
    // 1(true), 0(false)로 간주하여 계산한다.
    //
    int a = 0xca; // 0000 0000 0000 0000 0000 0000 1100 1010
    int b = 0x66; // 0000 0000 0000 0000 0000 0000 0110 0110
    System.out.println(a & b); // 0x42 = 66
                  // 0000 0000 0000 0000 0000 0000 0100 0010
    //System.out.println(a && b); // 컴파일 오류!
    
    System.out.println(a | b); // 0xee = 238
                  // 0000 0000 0000 0000 0000 0000 1110 1110
    //System.out.println(a || b); // 컴파일 오류!
  }
}

















