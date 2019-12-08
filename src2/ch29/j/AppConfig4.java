package ch29.j;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import ch29.j2.sub1.Sub1Car;

//@Configuration // 스프링 IoC 컨테이너에게 이 클래스를 직접 알려주기 때문에 이 애노테이션은 필요없다.
@ComponentScan(basePackages="ch29.j2")
public class AppConfig4 {
  
  // 애노테이션을 사용할 수 없는 경우 다음과 같이 수동으로 객체를 생성할 수 있다.
  // 이때 @Bean 애노테이션을 붙여 이 메서드를 호출하라고 스프링 IoC 컨테이너에게 알려줘야 한다.
  // 스프링 IoC 컨테이너는 이 메소드를 호출한 후 그 리턴 값을 메서드 이름으로 저장한다.
  // 만약 @Bean 애노테이션이 따로 이름을 지정한다면 그 이름으로 리턴 값을 저장한다.
  // 예) 다른 사람이 만든 클래스의 인스턴스를 우리 IoC 컨테이너에 등록하고 싶을 때 사용한다.
  //     => SqlSessionFactory, DataSource, PlatformTransactionManager 등
  @Bean("blackBox")
  public BlackBox blackBox22() {
    // 이 메서드의 이름이 리턴 값을 저장하는 이름으로 사용되기 때문에 
    // 메서드의 이름을 명사 형태로 짓는다.
    BlackBox obj = new BlackBox();
    obj.setMaker("비트박스");
    obj.setModel("비트비트");
    return obj;
  }
  
  // 스프링 IoC 컨테이너로부터 객체를 주입받고 싶다면 파라미터에 선언하라.
  // => 파라미터 이름으로 객체를 찾는 것이 아니라 파라미터 타입으로 객체를 찾는다.
  @Bean
  public Car car1(Sub1Car sc) {
    Car obj = new Car();
    obj.setMaker("비트자동차");
    obj.setModel("모델1");
    obj.setAuto(true);
    obj.setBlackBox(sc.getBlackBox());
    return obj;
  }
  
  //스프링 IoC 컨테이너를 파라미터로 주입 받을 수 있다.
  @Bean
  public Car car2(ApplicationContext iocContainer) {
    BlackBox bb = (BlackBox)iocContainer.getBean("blackBox");
    
    Car obj = new Car();
    obj.setMaker("비트자동차");
    obj.setModel("모델2");
    obj.setAuto(false);
    obj.setBlackBox(bb);
    
    return obj;
  }
  
}






