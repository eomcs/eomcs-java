package com.eomcs.design_pattern.observer.after;

import java.util.HashSet;

public class Car {
  
  // 옵저버 목록을 저장할 집합객체 준비
  // => 같은 옵저버가 중복 등록되지 않도록 한다.
  // => 등록 순서에 따라 통지할 필요는 없다.
  //
  HashSet<CarObserver> observers = new HashSet<>();
  
  // 옵저버를 관리하는 메서드 추가
  public void addObserver(CarObserver observer) {
    observers.add(observer);
  }
  
  public void removeObserver(CarObserver observer) {
    observers.remove(observer);
  }
  
  // 자동차의 시동을 걸 때 통지한다.
  public void notifyObserversOnStarted() {
    for (CarObserver observer : observers) {
      observer.carStarted();
    }
  }
  
  // 자동차를 시동을 끌 때 통지한다.
  public void notifyObserversOnStopped() {
    for (CarObserver observer : observers) {
      observer.carStopped();
    }
  }
  
  public void start() {
    System.out.println("시동을 건다.");
    
    notifyObserversOnStarted();
  }
  
  public void run() {
    System.out.println("달린다.");
  }
  
  public void stop() {
    System.out.println("시동을 끈다.");
    
    notifyObserversOnStopped();
  }
}






