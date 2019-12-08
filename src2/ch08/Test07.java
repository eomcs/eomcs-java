// 클래스 문법의 또 다른 용도 - 클래스 사용 후 => 계산을 수행하는 메서드를 별도의 클래스로 분리한다.
package ch08;

import java.util.Scanner;

public class Test07 {
  
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    
    System.out.print("계산식?(예: 12 * 2) ");
    int a = keyboard.nextInt();
    String op = keyboard.next();
    int b = keyboard.nextInt();
   
    keyboard.close();
    
    int result = 0;
    switch (op) {
      case "+": result = Calculator.plus(a, b); break;
      case "-": result = Calculator.minus(a, b); break;
      case "*": result = Calculator.multiple(a, b); break;
      case "/": result = Calculator.divide(a, b); break;
      default:
        System.out.println("지원하지 않는 연산자입니다.");
        return; // main() 메서드를 그만 실행하고 나간다. 즉 JVM 종료!
    }
    
    System.out.printf("%d %s %d = %d\n", a, op, b, result);
  }
  
  
}

/*
# 클래스 문법의 용도
1) 새 데이터 타입을 정의할 때
2) 관련 메서드를 분류할 때
   => 클래스 문법은 유지보수하기 쉽도록 관련 기능들을 묶어 분류할 때 사용할 수 있다.
 */















