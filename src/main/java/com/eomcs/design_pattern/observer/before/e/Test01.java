package com.eomcs.design_pattern.observer.before.e;

public class Test01 {

  public static void main(String[] args) {
    Car car = new Car();

    car.start();

    car.run();

    car.stop();

    // 업그레이드를 수행한 다음 시간이 지난 후
    // 4) 시동 끌 때 자동차 전조등을 자동으로 끄는 기능을 추가한다.
    // => Car의 stop() 메서드에 해당 코드 추가
  }

}


