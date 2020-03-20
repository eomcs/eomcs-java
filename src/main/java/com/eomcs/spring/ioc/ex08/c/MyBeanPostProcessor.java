package com.eomcs.spring.ioc.ex08.c;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

// Spring IoC 컨테이너에 새 기능을 추가하는 예:
// => 새 기능이 IoC 컨테이너가 생성한 객체를 사용해야 한다면,
// 객체 생성 후에 그 작업을 수행하면 된다.
// => 이렇게 개발자가 컨테이너의 기능을 확장할 수 있도록
// BeanPostProcessor라는 규칙을 제공한다.
//
// 즉 빈 생성 후에 어떤 작업을 수행할 객체를 만들고 싶다면?
// => BeanPostProcessor 규칙에 따라 클래스를 만들라!
//
public class MyBeanPostProcessor implements BeanPostProcessor {
  public MyBeanPostProcessor() {
    System.out.println("MyBeanPostProcessor()");
  }

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException {
    System.out.println("postProcessBeforeInitialization()");
    return null;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("postProcessAfterInitialization()");
    return null;
  }
}
