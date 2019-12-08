// &&, || vs &, |
package ch04;

public class Test12 {
  public static void main(String[] args) {
    // 괄호 연산
    int a = 5;
    int r = 10 / (a = 2);
    System.out.printf("%d, %d\n", a, r);
    // r = 10 / (a = 2)
    // r = 10 / (a 변수에 2를 저장한다)
    // r = 10 / (a 변수의 값을 리턴한다)
    // r = 10 / 2
    // r = 5
    
    // && 와 &의 차이점
    boolean b1 = true;
    boolean b2 = false && (b1 = false);
    System.out.printf("b1=%b, b2=%b\n", b1, b2);
    // && 연산자는 l-value의 값으로 결과를 유추할 수 있다면, r-value를 실행하지 않는다.
    
    b1 = true;
    b2 = false & (b1 = false);
    System.out.printf("b1=%b, b2=%b\n", b1, b2);
    // & 연산자는 l-value의 값으로 결과가 결정되었다 하더라도 r-value를 끝까지 실행한다.
    
    b1 = true;
    b2 = true || (b1 = false);
    System.out.printf("b1=%b, b2=%b\n", b1, b2);
    
    b1 = true;
    b2 = true | (b1 = false);
    System.out.printf("b1=%b, b2=%b\n", b1, b2);
  }
}

















