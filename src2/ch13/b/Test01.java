// 기능 확장 - 방법2) 상속 기능을 활용하기
package ch13.b;

public class Test01 {
  public static void main(String[] args) {
    // 다음 Calculator2는 Calculator의 기능을 상속 받았기 때문에 
    // Calculator2를 통해 Calculator의 기능을 호출할 수 있다.
    //
    Calculator2 c1 = new Calculator2();
    c1.plus(5); // Calculator의 메서드 
    c1.multiple(2); // Calculator2의 메서드 
    c1.minus(2); // Calculator의 메서드 
    c1.divide(4); // Calculator2의 메서드
    System.out.println(c1.getResult());
  }
}







