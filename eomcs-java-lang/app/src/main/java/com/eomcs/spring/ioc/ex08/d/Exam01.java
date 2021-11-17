// 의존 객체 주입 자동화하기 - 인스턴스 필드에 @Autowired 애노테이션 붙이기
package com.eomcs.spring.ioc.ex08.d;

import java.lang.reflect.Field;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.eomcs.spring.ioc.SpringUtils;

public class Exam01 {

  public static void main(String[] args) throws Exception {
    // @Autowired 애노테이션을 필드(인스턴스 변수)에 붙여도 된다.
    // => 그러면 의존 객체를 직접 변수에 주입한다.
    // => 셋터를 호출하지 않는다. 즉 셋터가 없어도 된다.
    // => 인스턴스 변수에 직접 의존 객체를 주입한다는 것은
    // 캡슐화(즉 외부에서 직접 인스턴스 변수에 접근하는 것을 막는 기법)를
    // 위배하는 측면이 있기 때문에
    // 이 방식은 "객체지향을 파괴하는 방식"이라는 비난을 받는다.
    //
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(//
        "com/eomcs/spring/ioc/ex08/d/application-context.xml");

    // Spring IoC 컨테이너에 들어 있는 객체 출력
    SpringUtils.printBeanList(iocContainer);

    // Car 객체를 꺼내 Engine 객체가 주입되었는지 확인해보자!
    System.out.println(iocContainer.getBean("c1"));


    // private 멤버는 직접 접근 불가!
    Car c2 = new Car();
    //    c2.model = "비트비트"; // private 멤버이기 때문에 접근 불가! 컴파일 오류!
    c2.setModel("비트비트"); 
    System.out.println(c2);


    // 정말?
    // => Reflection API 사용하면 private 멤버도 접근할 수 있다.
    Field f = Car.class.getDeclaredField("model");
    f.setAccessible(true); // private 멤버이지만 난 접근할래!!!
    f.set(c2, "오호라2");
    System.out.println(c2);

  }

}


