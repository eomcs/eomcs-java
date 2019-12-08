// @Component - <context:component-scan> 사용법 : 특정 클래스나 패키지 제외하기
package ch29.i;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ch29.SpringUtils;

public class Test02 {
  public static void main(String[] args) {
    ApplicationContext iocContainer = 
        new ClassPathXmlApplicationContext("ch29/i/application-context-02.xml");
    
    // @Component 애노테이션 붙은 클래스의 인스턴스를 자동으로 생성한다.
    //
    System.out.println("---------------------------------------");
    SpringUtils.printObjects(iocContainer);
    System.out.println("---------------------------------------");
    
  }
}






