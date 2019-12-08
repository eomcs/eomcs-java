package ch09;

public class Test03_2 {

  public static void main(String[] args) {
    // 계산하기
    // 식1) 2 * 3 - 2 + 7 = ?
    // 식2) 6 / 2 + 4 = ?
    
    // Calculator2 클래스에는 result  변수가 한 개만 있기 때문에
    // 두 개의 계산식을 동시에 수행할 수 없다.
    // 다음과 같이 한 개의 식을 모두 계산한 다음에 
    // 두 번째 식을 계산해야 한다.
    // => 이것이 클래스 필드의 한계이다.
    // => 해결책? 개별적으로 관리되어야 하는 값은 인스턴스 변수에 저장한다.
    
    Calculator2.plus(2); // 2
    Calculator2.multiple(3); // 6
    Calculator2.minus(2); // 4
    Calculator2.plus(7); // 11
    System.out.printf("결과 = %d\n", Calculator2.result);
    
    Calculator2.result = 0;
    Calculator2.plus(6); // 6
    Calculator2.divide(2); // 2
    Calculator2.plus(4); // 4
    System.out.printf("결과 = %d\n", Calculator2.result);
  }

}
