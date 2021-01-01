// 상속 : specialization
package com.eomcs.oop.ex05.j;

// 기존 클래스에 기능을 덧붙여 특별한 클래스를 만드는 것
// - 수퍼 클래스를 상속 받아 서브 클래스를 만드는 것을 "전문화(specialization)"라 부른다.
//
public class Exam01 {

  public static void main(String[] args) {
    Sedan s = new Sedan();
    Truck t = new Truck();

    s.doSunroof(true);
    t.dump();
  }

}
