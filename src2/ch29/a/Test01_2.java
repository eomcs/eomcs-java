// 스프링 IoC 컨테이너의 객체 목록
package ch29.a;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ch29.SpringUtils;

public class Test01_2 {
  public static void main(String[] args) throws Exception {
    ApplicationContext iocContainer1 = 
        new ClassPathXmlApplicationContext("ch29/a/application-context.xml");
    System.out.println("[XML IoC 컨테이너]");
    SpringUtils.printObjects(iocContainer1);
    
    
    ApplicationContext iocContainer2 = 
        new AnnotationConfigApplicationContext(AppConfig.class);
    System.out.println("[Java Config IoC 컨테이너]");
    SpringUtils.printObjects(iocContainer2);
    
  }
  
  
}






