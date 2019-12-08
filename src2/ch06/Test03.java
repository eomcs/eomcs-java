// 메서드 - 메서드의 리턴 값
package ch06;

public class Test03 {
  public static void main(String[] args) {
    // 값을 리턴하는 메서드 호출
    // => 리턴 값을 받을 변수를 준비한다.
    // => 변수에 리턴 값을 받는다.
    // => 리턴 값과 변수의 타입이 같아야 한다.
    int i;
    //i = m1(); // 컴파일 오류! 리턴 값의 타입과 변수의 타입이 맞지 않다.
    String s;
    s = m1(); // OK!
    System.out.println(s);
    
    i = m2(); // OK!
    System.out.println(i);
    
    // 리턴 값을 사용할 일이 없다면 메서드 호출할 때 받지 않아도 된다.
    m1();
    m2();
  }
  
  static String m1() {
    // 메서드를 실행한 후 호출자에게 값을 리턴하려면 
    return "홍길동";
  }
  
  static int m2() {
    return 100;
  }
}















