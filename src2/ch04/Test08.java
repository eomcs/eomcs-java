// 전위 연산자 - ++a, --a
package ch04;

public class Test08 {
  public static void main(String[] args) {
    int a = 5;
    int r = ++a;
    System.out.printf("%d, %d\n", a, r);
    // r = ++a 연산 순서
    // 1) ++a 연산을 먼저 수행 => a 변수의 값을 1 증가시킨다.
    // 2) a 값을 리턴한다. => r = 6;
    // 3) = 연산자 수행 => r 변수에 6 값을 넣는다.
    // 결론!
    // => 전위 연산자는 먼저 변수의 값을 -- 하거나 ++ 한 후에 
    //    그 자리에 변수의 값을 놓는다.
    a = 5;
    r = --a;
    System.out.printf("%d, %d\n", a, r);
    // r = --a 연산 순서
    // 1) --a를 먼저 실행 => a = 4
    // 2) a변수의 값을 리턴 => r = 4;
    // 3) = 연산을 수행 => r 변수에 4 값을 넣는다.
    
    a = 5;
    r = --a + --a / --a;
    System.out.printf("%d, %d\n", a, r);
    // 연산 순서
    // r = 4 + --a / --a;
    // r = 4 + 3 / --a;
    // r = 4 + 3 / 2;
    // r = 4 + 1;
    // r = 5;
  }
}

















