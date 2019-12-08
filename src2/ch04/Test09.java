// 후위 연산자 - a++, a--
package ch04;

public class Test09 {
  public static void main(String[] args) {
    int a = 5;
    int r = a++;
    System.out.printf("%d, %d\n", a, r);
    // 후위 연산
    // => 변수의 값을 먼저 그 자리에 놓는다.
    // => 그런 후 변수의 값을 증가시킨다.
    //
    // r = a++;
    // 1) r = 5;  ==> 주의! 변수의 값을 놓기만 할 뿐 아직 = 연산을 수행하지 않는다.
    //                = 연산은 r-value를 모두 처리한 후에 맨 마지막에 수행된다.
    // 2) a++ 실행 ==> a = 6;
    // 3) = 연산 실행 => r 변수에 5를 저장한다.
    
    a = 5;
    r = a++ + a++ * a++;
    System.out.printf("%d, %d\n", a, r);
    // r = a++ + a++ * a++;
    // r = 5 + a++ * a++;   a = 6
    // r = 5 + 6 * a++; a = 7
    // r = 5 + 6 * 7;   a = 8
    // r = 5 + 42;  a = 8
    // r = 47; a = 8
    // 
    
    a = 5;
    a = a++;
    System.out.println(a);
    // a = a++;
    // a = 5;   a = 6
    // a <= 5
    
    int x = 5;
    int y = 10;
    int z = ++x * y--;
    System.out.println(z);
    
  }
}

















