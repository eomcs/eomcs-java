package com.eomcs.oop.ex09.h;

public abstract class AbstractCar implements Car, CarCheckInfo {
  int gas;
  int brakeOil;
  int engineOil;

  String maker;
  String model;
  int cc;

  // 자동차 기본 기능
  public String getMaker() {
    return maker;
  }
  public void setMaker(String maker) {
    this.maker = maker;
  }
  public String getModel() {
    return model;
  }
  public void setModel(String model) {
    this.model = model;
  }
  public int getCc() {
    return cc;
  }
  public void setCc(int cc) {
    this.cc = cc;
  }

  // Car 규칙에 따라 기능 구현
  @Override
  public void start() {
    System.out.println("시동 건다!");
  }

  @Override
  public void shutdown() {
    System.out.println("시동 끈다!");
  }

  @Override
  public abstract void run();

  // CarCheckInfo 규칙에 따라 기능 구현하기
  @Override
  public int getGas() {
    return gas;
  }
  @Override
  public int getBrakeOil() {
    return brakeOil;
  }
  @Override
  public int getEngineOil() {
    return engineOil;
  }
}
