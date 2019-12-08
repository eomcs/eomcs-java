// 다형적 변수의 사용
package ch14.e;

public class Test02 {

  public static void main(String[] args) {
    PickupTruck c1 = new PickupTruck();
    SUV c2 = new SUV();
    Convertible c3 = new Convertible();
    DumpTruck c4 = new DumpTruck();
    
    test(c1);
    test(c2);
    test(c3);
    test(c4);
  }
  
  // Car 레퍼런스는 Car 의 모든 종류(서브 클래스)의 인스턴스 주소를 담을 수 있다.
  // => 이렇게 한 변수가 다양한 타입의 값을 담을 수 있는 것을 "다형적 변수"라고 부른다.
  // => 다양적 변수를 사용하면 훨씬 더 유연하게 객체를 다룰 수 있다.
  static void test(Car car) {
    System.out.println("---------------------");
    car.run(); // car 변수가 실제 가리키는 인스턴스의 오버라이딩 메서드이다.
    car.stop(); // Car의 stop()
    car.run(); // car 변수가 실제 가리키는 인스턴스의 오버라이딩 메서드이다.
    car.stop(); // Car의 stop()
    System.out.println("---------------------");
  }
  

}
