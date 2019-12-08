// 다형적 변수의 사용 II
package ch14.e;

import java.sql.Date;
import java.util.Calendar;

public class Test03 {

  public static void main(String[] args) {
    // 레퍼런스는 항상 하위 타입의 인스턴스를 가리킬 수 있다.
    Car c1 = new Car();
    c1 = new Sedan();
    c1 = new SUV();
    c1 = new Convertible();
    c1 = new Truck();
    c1 = new DumpTruck();
    c1 = new PickupTruck();
    
    Sedan c2 = new Sedan();
    c2 = new SUV();
    c2 = new Convertible();
    //c2 = new Car(); // 상위 타입의 인스턴스를 가리킬 수 없다.
    //c2 = new Truck(); // 물론 상속 관계가 없는 다른 타입을 인스턴스도 가리킬 수 없다.
    //c2 = new DumpTruck();
    //c2 = new PickupTruck();
    
    Object obj = new Object();
    obj = new String();
    obj = new Car();
    obj = new Sedan();
    obj = new Truck();
    obj = new SUV();
    obj = new DumpTruck();
    obj = Date.valueOf("2019-1-1");
    obj = Calendar.getInstance();
    obj = new java.io.File("test.ok");
    
  }

}








