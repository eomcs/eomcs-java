// 상수 변수를 활용하여 분류 코드를 다루기
package com.eomcs.oop.ex11.b;

public class Exam0110 {
    public static void main(String[] args) {
        Product p = new Product();
        p.category = Product.APPLIANCE_TV;
        p.name = "울트라비전 뷰";
        p.price = 2000000;
    }
}
