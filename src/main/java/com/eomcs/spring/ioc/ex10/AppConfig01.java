package com.eomcs.spring.ioc.ex10;

import org.springframework.context.annotation.Bean;

public class AppConfig01 {
  //AnnotationConfigApplicationContext 는 @Bean이 붙은 메서드를 호출하여 
  //그 리턴 값을 컨테이너에 저장한다.
  //객체의 이름을 지정하지 않으면 메서드 이름을 객체 이름으로 사용한다.
  @Bean 
  public Car getCar() {
    Car c = new Car(null);
    c.setMaker("비트자동차");
    c.setModel("티코");
    c.setCc(890);
    c.setAuto(true);
    return c;
  }

  @Bean("car") // 이렇게 이 메서드가 리턴한 객체를 보관할 때 사용할 이름을 지정할 수 있다.
  public Car getCar2() {
    Car c = new Car(null);
    c.setMaker("비트자동차");
    c.setModel("티코");
    c.setCc(890);
    c.setAuto(true);
    return c;
  }

  // 실무에서는 스프링 설정 용으로 사용하는 클래스에서
  // 객체를 리턴하는 메서드를 만들 때 그 메서드의 이름을 객체 이름과 같게 짓는다. 
  // => 보통 어떤 값을 리턴할 때는 getXxx()라는 이름으로 메서드를 만드는데,
  //    이처럼 객체이름으로 사용할 수 있도록 메서드를 만드는 경우도 있으니
  //    당황하지 말라!
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









