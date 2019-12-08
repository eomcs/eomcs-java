package ch09.test;

public class Test {

  public static void main(String[] args) {
    // 계산하기
    // 식1) 2 * 3 - 2 + 7 = ?
    // 식2) 6 / 2 + 4 = ?
    
    // "계산식1"의 결과를 저장할 메모리 생성
    Calculator calc1 = new Calculator();
    
    // "계산식2"의 결과를 저장할 메모리 생성
    Calculator calc2 = new Calculator();
    
    calc1.plus(2); // 2
    calc2.plus(6); // 6
    
    calc1.multiple(3); // 6
    calc2.divide(2); // 2
    
    calc1.minus(2); // 4
    calc2.plus(4); // 4
    
    calc1.plus(7); // 11
    
    System.out.printf("계산식1의 결과 = %d\n", calc1.result);
    System.out.printf("계산식2의 결과 = %d\n", calc2.result);
  }

}
