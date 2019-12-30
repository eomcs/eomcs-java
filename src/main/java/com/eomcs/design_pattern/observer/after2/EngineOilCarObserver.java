package com.eomcs.design_pattern.observer.after2;

public class EngineOilCarObserver extends AbstractCarObserver {
  @Override
  public void carStarted() {
    System.out.println("엔진 오일 유무 검사");
    
  }
}
