// BeanPostProcessor - @Autowired + @Qualifier = @Resource (자바 표준 애노테이션)
package ch29.h;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ch29.SpringUtils;

public class Test08 {
  public static void main(String[] args) {
    ApplicationContext iocContainer = 
        new ClassPathXmlApplicationContext("ch29/h/application-context-08.xml");
    
    //@Qualifier("객체이름")를 사용하여 여러 개의 객체 중에서 어떤 것을 주입할 지 지정한다.
    //
    System.out.println("---------------------------------------");
    SpringUtils.printObjects(iocContainer);
    System.out.println("---------------------------------------");
    
    System.out.println(iocContainer.getBean("c1"));
  }
}






