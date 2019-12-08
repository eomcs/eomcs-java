// Java config - @Configuration 애노테이션
package ch29.j;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ch29.SpringUtils;

public class Test02 {
  public static void main(String[] args) {
    
    // AnnotationConfigApplicationContext 컨테이너에 패키지명을 알려주면 
    // 그 패키지에 있는 @Component, @Service, @Repository, @Controller 객체를 생성한다.
    // 만약 클래스들 중에서 @Configuration 애노테이션이 붙은 클래스가 있다면 
    // Java config 클래스로 인식하여 자동으로 처리한다.
    ApplicationContext iocContainer = 
        new AnnotationConfigApplicationContext("ch29.j");
    
    System.out.println("---------------------------------------");
    SpringUtils.printObjects(iocContainer);
    System.out.println("---------------------------------------");
  }
}






