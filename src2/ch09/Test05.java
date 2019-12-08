// 인스턴스 필드의 등장
package ch09;

public class Test05 {
  
  public static void main(String[] args) {
    
    // Calculator3를 사용하여 두 계산식을 동시에 실행하기
    // 식1) 2 * 3 - 2 + 7 = ?
    // 식2) 6 / 2 + 9 = ?
    
    // Calculator3 설계도에 따라 result 변수를 준비한다.
    Calculator3 c1 = new Calculator3();
    Calculator3 c2 = new Calculator3();
        
    Calculator3.plus(c1/*result 변수가 있는 인스턴스 주소*/, 2);
    Calculator3.plus(c2, 6);
    
    Calculator3.multiple(c1, 3);
    Calculator3.divide(c2, 2);
    
    Calculator3.minus(c1, 2);
    Calculator3.plus(c2, 9);
    
    Calculator3.plus(c1, 7); 
    
    System.out.println(c1.result);
    System.out.println(c2.result);
    
    // 오호라~~ result를 인스턴스 필드로 선언하면서
    // 계산 결과를 개별적으로 관리할 수 있게 되었다.
    // 
    // 그런데 메서드를 호출할 때마다 메서드가 사용할 result를 알려주기 위해
    // 파라미터로 인스턴스 주소를 넘겨 줘야 하는 불편함이 생겼다.
    // 해결책?
    // => 인스턴스 메서드를 사용하라!
    
  }
}
















