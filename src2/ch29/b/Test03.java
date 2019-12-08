// 객체 생성 시점과 범위
package ch29.b;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import ch29.SpringUtils;

public class Test03 {
  public static void main(String[] args) {
    ApplicationContext iocContainer = 
        new ClassPathXmlApplicationContext("ch29/b/application-context-03.xml");
    
    SpringUtils.printTypes(iocContainer);
    System.out.println("---------------------------------------");
    
    // Singleton 객체는 IoC 컨테이너를 생성할 때 미리 생성된다. 
    // getBean()을 호출할 때는 미리 생성된 객체를 리턴한다.
    Car obj1 = (Car) iocContainer.getBean("c1");
    Car obj2 = (Car) iocContainer.getBean("c1");
    Car obj3 = (Car) iocContainer.getBean("c1");
    
    System.out.println(obj1 == obj2); // true
    System.out.println(obj1 == obj3); // true
    
    // scope 속성에 singleton 이라고 명시할 수 있다.
    Car2 obj11 = (Car2) iocContainer.getBean("c2");
    Car2 obj12 = (Car2) iocContainer.getBean("c2");
    Car2 obj13 = (Car2) iocContainer.getBean("c2");
    
    System.out.println(obj11 == obj12); // true
    System.out.println(obj11 == obj13); // true
    
    // scope이 prototype 일 경우 미리 객체를 생성하지 않는다.
    // getBean()을 호출했을 때 생성한다. 
    // 그리고 getBean()을 호출할 때 마다 새 객체를 만들어 리턴한다.
    Car3 obj21 = (Car3) iocContainer.getBean("c3");
    Car3 obj22 = (Car3) iocContainer.getBean("c3");
    Car3 obj23 = (Car3) iocContainer.getBean("c3");
    
    System.out.println(obj21 == obj22); // false
    System.out.println(obj21 == obj23); // false
    System.out.println(obj22 == obj23); // false
  }
}






