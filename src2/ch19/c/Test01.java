// static nested class 사용 전 : 상수를 다른 클래스로 분류하기 
package ch19.c;

public class Test01 {

  public static void main(String[] args) {
    Product p = new Product();
    p.maker = "비트컴퓨터";
    p.title = "비트마우스";
    p.price = 98000;
    
    // 상수를 Category 클래스로 옮긴 후에 사용하기 
    p.category = Category.COMPUTER_MOUSE_BLUETOOTH;
    
    // 개선점:
    // => 카테고리의 상수 이름이 긴 이름으로 되어 있다.
    // => 만약 한 계층에 하위 분류를 추가한다면 underscore(_)를 사용하여 분류명을 길게 써야 한다.
    // => 분류명 관리가 불편하다.
    //
    // 해결책:
    // => static nested 클래스를 사용하여 각 계층별로 분류를 관리하게 한다.

  }

}









