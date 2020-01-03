package com.eomcs.spring.ioc.ex11;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@ComponentScan(
    basePackages="bitcamp.java106.step11",
    excludeFilters=@ComponentScan.Filter(
        type=FilterType.REGEX, 
        pattern="bitcamp.java106.step11.b.*")
    )
public class AppConfig01 {
  @Bean 
  public Car car2() {
    Car c = new Car(null);
    c.setMaker("비트자동차");
    c.setModel("티코");
    c.setCc(890);
    c.setAuto(true);
    return c;
  }
}









