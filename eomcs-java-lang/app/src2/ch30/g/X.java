package ch30.g;

import org.springframework.stereotype.Component;

@Component
public class X {
  
  public int calculate(int i, int j, String op) {
    System.out.println("X.calculate()");
    switch (op) {
      case "+": return i + j;
      case "-": return i - j;
      case "*": return i * j;
      case "/": return i / j;
      default:
        throw new RuntimeException("지원하지 않는 연산자입니다.");
    }
  }
}
