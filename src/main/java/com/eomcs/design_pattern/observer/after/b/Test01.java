package com.eomcs.design_pattern.observer.after.b;

public class Test01 {

  public static void main(String[] args) {

    Car car = new Car();

    car.addCarObserver(new SafeBeltCarObserver());

    car.start();

    car.run();

    car.stop();

    // 프로젝트 완료한 다음 시간이 지난 후
    // 1) 자동차의 시동을 걸 때 안전벨트 착용 여부를 검사하는 기능을 추가한다.
    // - 자동차의 시동을 걸릴 때 보고를 받을 객체(SafeBeltCarObserver)를 준비한다.
    // - 시동 걸 때 수행할 기능을 정의한다.
    // carStarted() 메서드 정의
    // - Car 객체에 관찰자를 등록한다.
  }

}


