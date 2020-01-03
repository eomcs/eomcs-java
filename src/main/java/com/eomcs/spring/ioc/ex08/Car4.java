package com.eomcs.spring.ioc.ex08;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;

// 의존 객체 Engine 주입 - 인스턴스 변수에 @Autowired를 붙여도 된다.
public class Car4 {
  String model;
  String maker;
  int cc;
  boolean auto;
  Date createdDate;

  Engine engine;

  // 만약 실행 중에 의존 객체를 교체해서는 안된다면,
  // 임의로 의존 객체를 교체하지 못하도록 final을 붙여라! 
  public Car4(final Engine engine) {
    System.out.println("Car4()");
    this.engine = engine;
  }


  @Override
  public String toString() {
    return "Car [model=" + model + ", maker=" + maker + ", cc=" + cc + ", auto=" + auto + ", createdDate="
        + createdDate + ", engine=" + engine + "]";
  }
  public Engine getEngine() {
    return engine;
  }

  public void setEngine(Engine engine) {
    System.out.println("Car.setEngine()");
    this.engine = engine;
  }

  public boolean isAuto() {
    return auto;
  }
  public void setAuto(boolean auto) {
    this.auto = auto;
  }
  public Date getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
  public String getModel() {
    return model;
  }
  public void setModel(String model) {
    this.model = model;
  }
  public String getMaker() {
    return maker;
  }
  public void setMaker(String maker) {
    this.maker = maker;
  }
  public int getCc() {
    return cc;
  }
  public void setCc(int cc) {
    this.cc = cc;
  }


}
