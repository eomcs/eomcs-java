package ch13.g;

public class Test01 {

  public static void main(String[] args) {
    // 자동차를 다루기 위해 Car 클래스를 정의하였다.
    // 그리고 정의한 클래스를 다음과 같이 사용한다.
    Car c = new Car();
    c.model = "소나타";
    c.cc = 1980;
    c.run();
    c.stop();
    
    System.out.println("------------------");
    
    // Car 클래스를 사용하다가 덤프트럭을 다룰 필요가 있어서
    // Car 클래스의 기능을 확장한 DumpTruck을 만들었다.
    // 그리고 그 클래스를 사용한다.
    DumpTruck c2 = new DumpTruck();
    c2.model = "타이탄v9";
    c2.cc = 12000;
    c2.run();
    c2.stop();
    c2.dump();
    
    // specialization
    // => 특정 클래스를 사용하다가 추가 기능이 필요해서 
    //    상속을 통해 서브 클래스를 정의하는 것.
    // => 수퍼 클래스도 직접 사용하고 서브 클래스도 직접 사용한다.

  }

}






