// 프로퍼티 값 설정 - Properties 프로퍼티 값 설정하기
package ch29.e;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test04 {
  public static void main(String[] args) {
    ApplicationContext iocContainer = 
        new ClassPathXmlApplicationContext("ch29/e/application-context-04.xml");
    
    System.out.println("---------------------------------------");
    
    System.out.println(iocContainer.getBean("t1"));
  }
}






