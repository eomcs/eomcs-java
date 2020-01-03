package com.eomcs.spring.ioc.ex08;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

// 빈 생성 후에 어떤 작업을 수행할 객체를 만들고 싶다면?
// => BeanPostProcessor 규칙에 따라 클래스를 만들라!
public class MyBeanPostProcessor implements BeanPostProcessor {
  public MyBeanPostProcessor() {
    System.out.println("MyBeanPostProcessor()");
  }
  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("postProcessBeforeInitialization()");
    return null;
    //return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
  }
  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("postProcessAfterInitialization()");
    return null;
    //return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
  }
}
