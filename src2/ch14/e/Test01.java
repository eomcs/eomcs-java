// 다형적 변수의 필요성
package ch14.e;

public class Test01 {

  public static void main(String[] args) {
    PickupTruck c1 = new PickupTruck();
    SUV c2 = new SUV();
    Convertible c3 = new Convertible();
    DumpTruck c4 = new DumpTruck();
    
    // 위에서 생성한 각 인스턴스를 테스트하려면 
    // 다음과 같이 그 타입에 맞는 test() 메서드를 각각 정의해야 한다.
    // test() 메서드의 코드는 같지만, 타입이 다르기 때문에 복/붙 해서라도 개별적으로 정의해야 한다.
    // 그냥 한 개의 test() 메서드로 처리하는 방법은 없을까?
    // 그게 가능하려면 test() 메서드의 파라미터가 모든 자동차의 인스턴스 주소를 받을 수 있어야 한다.
    // => 타입이 다르기 때문에 같은 코드를 공유할 수 없는 문제를 해결하기 위해 
    //    등장한 문법이 "다형적 변수(polymorphic variable)"이다.
    // => 한 개의 변수가 다양한 타입의 값을 받을 수 있는 것을 말한다.
    
    test(c1);
    test(c2);
    test(c3);
    test(c4);
  }
  
  static void test(PickupTruck car) {
    System.out.println("---------------------");
    car.run();
    car.stop();
    car.run();
    car.stop();
    System.out.println("---------------------");
  }
  
  static void test(SUV car) {
    System.out.println("************************");
    car.run();
    car.stop();
    car.run();
    car.stop();
    System.out.println("************************");
  }
  
  static void test(Convertible car) {
    System.out.println("************************");
    car.run();
    car.stop();
    car.run();
    car.stop();
    System.out.println("************************");
  }
  
  static void test(DumpTruck car) {
    System.out.println("************************");
    car.run();
    car.stop();
    car.run();
    car.stop();
    System.out.println("************************");
  }

}
