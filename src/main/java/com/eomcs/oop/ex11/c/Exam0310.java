// 상수 코드를 스태틱 중첩 클래스로 다루기
package com.eomcs.oop.ex11.c;

import com.eomcs.oop.ex11.c.step5.Product;

public class Exam0310 {
  public static void main(String[] args) {
    Product p = new Product();
    p.category = Category2.appliance.TV;
    p.name = "울트라비전 뷰";
    p.price = 2000000;
  }
}
