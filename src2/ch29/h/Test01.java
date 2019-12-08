// BeanPostProcessor - 스프링 IoC 컨테이너가 빈을 생성한 후 보고하는 규칙
package ch29.h;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {
  public static void main(String[] args) {
    ApplicationContext iocContainer = 
        new ClassPathXmlApplicationContext("ch29/h/application-context-01.xml");
    
    System.out.println("---------------------------------------");
    
    //System.out.println(iocContainer.getBean("c1"));
  }
}






