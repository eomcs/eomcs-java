package com.eomcs.design_pattern.observer.before.c;

public class Test01 {

  public static void main(String[] args) {
    Car car = new Car();

    car.start();

    car.run();

    car.stop();

    // 업그레이드를 수행한 다음 시간이 지난 후
    // 2) 자동차 시동 걸 때 엔진 오일 검사 기능을 추가한다.
    // => Car 클래스의 start() 메서드에 해당 기능을 수행하는 코드를 추가한다.
  }

}


