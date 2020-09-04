package com.eomcs.oop.ex09.h;

public interface CarCheckInfo {
  int getGas();
  int getBrakeOil();
  int getEngineOil();

  // 인터페이스에서 스태틱 메서드는 보통 
  // 그 인터페이스를 구현한 객체를 다루는 일을 한다.
  static boolean validate(CarCheckInfo carInfo) {
    if (carInfo.getBrakeOil() == 0 ||
        carInfo.getEngineOil() == 0 ||
        carInfo.getGas() == 0) {
      return false;
    }
    return true;
  }
}
