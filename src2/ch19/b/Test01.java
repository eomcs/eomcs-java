// static nested class 사용 전 : 상수를 다른 클래스로 분류하기 전 
package ch19.b;

public class Test01 {

  public static void main(String[] args) {
    Product p = new Product();
    p.maker = "비트컴퓨터";
    p.title = "비트마우스";
    p.price = 98000;
    
    // Product에 정의된 상수를 이용하여 분류를 지정한다.
    p.category = Product.COMPUTER_MOUSE;
    
    // 문제점:
    // => 분류 항목이 추가될 때마다 Product 클래스를 변경해야 한다.
    // => Product 클래스가 변경되면 기존에 작성한 코드가 영향을 받는다.
    // => 한 클래스가 분류 정보와 같은 다른 정보까지 관리하면 유지보수에도 좋지 않다.
    // 해결책:
    // => 분류 코드를 정의한 상수를 다른 클래스로 분리해보자!

  }

}









