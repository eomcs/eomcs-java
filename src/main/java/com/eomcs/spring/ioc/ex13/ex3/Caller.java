package com.eomcs.spring.ioc.ex13.ex3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Caller {

  @Autowired X x;

  public void test() {
    System.out.println("test()..... 시작");

    int result = x.m1(10, 2);
    System.out.printf("result: %d\n", result);

    result = x.m1(10, 0);
    System.out.printf("result: %d\n", result);

    System.out.println("test()..... 끝");
  }
}
