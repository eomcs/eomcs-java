// 의존 객체 주입 자동화하기 - 셋터에 @Autowired 애노테이션 붙이기 
package com.eomcs.spring.ioc.ex08;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eomcs.spring.ioc.SpringUtils;

public class Exam02 {

  public static void main(String[] args) {
    // @Autowired 애노테이션을 처리할 객체를 등록해야만 
    // 의존 객체가 자동으로 주입된다.
    // [동작원리]
    // 1) 스프링 IoC 컨테이너는 객체를 만든다.
    // 2) 프로퍼티 값을 설정한다.
    // 3) 객체 생성 후 @Autowired 애노테이션을 처리한다.
    //    => 단 @Autowired 애노테이션을 읽고 처리할 객체가 등록되어 있어야 한다.
    //    
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "bitcamp/java106/step08/application-context-02.xml");




    // Car 객체를 꺼내 Engine 객체가 주입되었는지 확인해보자!
    System.out.println(iocContainer.getBean("c1"));
  }

}





