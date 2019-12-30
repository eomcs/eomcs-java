package com.eomcs.design_pattern.observer.after;

public class Test01 {

  public static void main(String[] args) {
    Car car = new Car();
    
    car.start();
    
    car.run();
    
    car.stop();
    
    // Observer 디자인 패턴 
    // => 어떤 클래스에 변화가 일어 났을 때 다른 클래스에게 통보해 주는 것.
    // => 통보는 받는 클래스를 "옵저버(observer)"라 부른다.
    //    또는 "리스너(listener)"라 부른다.
    // => 보통 이벤트를 다루는 시스템에서 많이 사용하는 패턴이다.
    // 
    
    // Observer 패턴 적용
    // 1) 상태가 바뀌었을 때 통지할 규칙을 정의한다.
    //    => 클래스의 상태가 바뀌었을 때 다른 클래스에게 통지해야 하는데,
    //       그 방법이 메서드를 호출하는 것이다.
    //    => 즉 통지를 받을 클래스가 가져야할 메서드 규칙을 정의한다.
    //    => CarObserver 인터페이스 정의 
    //
    // 2) 상태가 바뀔 때 마다 다른 클래스에게 통지하는 기능을 추가한다.
    //    => 상태가 바뀌었을 때 등록된 클래스에게 통지할 수 있도록 코드를 추가한다.
    //    => Car 클래스에서 다음 메서드 추가
    //       - addObserver(CarObserver) : 옵저버 추가
    //       - removeObserver(CarObserver) : 등록된 옵저버 제거
    //       - notifyObserversOnStarted() : 옵저버에 통지
    //       - notifyObserversOnStopped() : 옵저버에 통지
    //
    // 3) 특정 상태에 있을 때 작업을 수행하는 클래스를 정의한다.
    //    => 상태가 바뀐 클래스에서 규칙에 따라 호출할 수 있도록 
    //       약속된 인터페이스를 구현한다.
    //    => CarObserver 인터페이스를 구현한 클래스 정의
    //    => 예) 자동차 안전벨트 점검 옵저버 => SafeBeltCarObserver
    //
    System.out.println("------------------------------");
    
    // Car 클래스에 기능을 추가해보자!
    // => 옵저버 패턴을 적용하기 전에는 직접 Car 클래스에 코드를 추가하였다.
    // => 옵저버 패턴을 적용한 후에는 Car 클래스가 특정 상태에 놓일 때 작업을 수행하는 
    //    클래스를 쉽게 추가할 수 있다.
    //
    // 예) 1월 20일 - 자동차 시동을 걸 때 안전 벨트 착용 여부를 검사하는 기능을 추가
    // => 다음과 같이 단순히 자동차 시동을 걸 때 통지를 받을 옵저버를 추가하면 된다.
    // => Car 클래스는 손댈 필요가 없다.
    //
    car.addObserver(new SafeBeltCarObserver());
    car.start();
    car.run();
    car.stop();
    System.out.println("------------------------------");
    
    // 예) 2월 30일 - 자동차 시동을 걸 때 엔진 오일 유무를 검사하는 기능을 추가 
    car.addObserver(new EngineOilCarObserver());
    car.start();
    car.run();
    car.stop();
    System.out.println("------------------------------");
    
    // 예) 3월 2일 - 자동차 시동을 걸 때 브레이크 오일 유무를 검사하는 기능을 추가
    car.addObserver(new BreakOilCarObserver());
    car.start();
    car.run();
    car.stop();
    System.out.println("------------------------------");
    
    // 예) 4월 15일 - 자동차 시동을 끌 때 전조등 자동 끄기 기능을 추가
    car.addObserver(new LightOffCarObserver());
    car.start();
    car.run();
    car.stop();
    System.out.println("------------------------------");
    
    // 예) 5월 5일 - 자동차 시동을 끌 때 썬루프 자동 닫기 기능을 추가
    car.addObserver(new SunRoofCloseCarObserver());
    car.start();
    car.run();
    car.stop();
    System.out.println("------------------------------");
  }

}








