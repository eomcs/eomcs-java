// 계산 결과는 그 변수의 타입과 같다.
package ch03;

public class Test03 {
  public static void main(String[] args) {
    int a = 5;
    int b = 2;
    System.out.println(a / b); // int와 int의 계산 결과는 int이다. 
    
    float f1 = 9876.543f;
    float f2 = 12.34567f;
    System.out.println(f1);
    System.out.println(f2);
    System.out.println(f1 + f2); // float과 float의 계산 결과는 float이다.
    // 따라서 계산 결과가 4바이트 float의 크기를 넘어가면 적절히 반올림하여 나머지 값을 버린다.
    
    double d1 = 9876.543;
    double d2 = 12.34567;
    System.out.println(d1);
    System.out.println(d2);
    System.out.println(d1 + d2); // double과 double의 계산 결과는 double
    
    // 그래서 보통 부동소수점을 다룰 때는 double을 많이 사용한다.
    // 자바는 double 부동소수점의 리터럴을 다룰 때 접미사를 붙이지 않는다.
    // 정수의 경우는 4바이트 정수 리터럴을 많이 사용하기 때문에 
    // 4바이트 정수 리터럴에 접미사를 붙이지 않는다.
    
  }
}















