package com.eomcs.design_pattern.observer.before.d;

public class Test01 {

  public static void main(String[] args) {
    Car car = new Car();

    car.start();

    car.run();

    car.stop();

    // 업그레이드를 수행한 다음 시간이 지난 후
    // 3) 자동차 시동 걸 때 브레이크 오일 검사 기능을 추가한다.
    // => Car의 start() 메서드에 해당 코드 추가
  }

}


