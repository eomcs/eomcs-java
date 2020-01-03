package com.eomcs.spring.ioc.ex13.ex2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Caller {

  @Autowired X x;
  @Autowired Y y;
  @Autowired Z z;

  public void test() {
    System.out.println("test()..... 시작");
    x.m1();
    y.m2();
    z.m2();
    System.out.println("test()..... 끝");
  }
}
