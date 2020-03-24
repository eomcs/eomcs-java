// AOP(Aspect-Oriented Programming) - AOP 필터 적용 후
package com.eomcs.spring.ioc.ex13.b;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.eomcs.spring.ioc.SpringUtils;

public class Exam05 {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext iocContainer = new ClassPathXmlApplicationContext(//
        "com/eomcs/spring/ioc/ex13/b/application-context-05.xml");

    SpringUtils.printBeanList(iocContainer);

    Caller caller = iocContainer.getBean(Caller.class);
    caller.test();
  }

}


