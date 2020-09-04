package com.eomcs.oop.ex09.h;

public abstract class Car implements CarCheckInfo {
  int gas;
  int brakeOil;
  int engineOil;

  String maker;
  String model;
  int cc;

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

  public void start() {
    System.out.println("시동 건다!");
  }

  public void shutdown() {
    System.out.println("시동 끈다!");
  }

  public abstract void run();
}
