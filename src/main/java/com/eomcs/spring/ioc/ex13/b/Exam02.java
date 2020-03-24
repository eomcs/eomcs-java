// AOP(Aspect-Oriented Programming) - AOP 필터 적용 후
package com.eomcs.spring.ioc.ex13.b;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.eomcs.spring.ioc.SpringUtils;

public class Exam02 {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext iocContainer = new ClassPathXmlApplicationContext(//
        "com/eomcs/spring/ioc/ex13/b/application-context-02.xml");

    SpringUtils.printBeanList(iocContainer);

    Caller caller = iocContainer.getBean(Caller.class);
    caller.test();
  }

}


