// 다형적 변수와 오버라이딩 메서드, 그리고 추상 메서드
package ch14.f;

public class Test03 {

  public static void main(String[] args) {
    
    // 추상 클래스는 인스턴스를 생성할 수 없다.
    // 그래서 추상 메서드를 호출할 수 없다.
    //Car c = new Car(); // 컴파일 오류!
    
    // Car 클래스를 사용하려면 Car의 서브 클래스의 인스턴스를 만들어 써야 한다.
    Car c = new Sedan(); // OK
    
    // Car의 레퍼런스 c를 가지고 run()을 호출하면 
    // 실제 c가 가리키는 Sedan의 run()이 호출된다.
    c.run();
    
    runCar(new Sedan());
    runCar(new SUV());
    runCar(new Convertible());
  }
  
  public static void runCar(Car car) {
    car.run();
  }

}






