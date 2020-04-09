package com.eomcs.spring.ioc.ex10.c;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import com.eomcs.spring.ioc.ex10.Car;

@Configuration

// .properties 파일을 로딩할 때 @PropertySource 애노테이션을 사용한다.
@PropertySource({//
    "classpath:com/eomcs/spring/ioc/ex10/c/jdbc.properties", //
    "classpath:com/eomcs/spring/ioc/ex10/c/jdbc2.properties"})
public class AppConfig {

  // @PropertySource를 통해 로딩된 프로퍼티 값을 사용하고 싶다면
  // Environment 타입의 객체를 주입 받아라!
  @Autowired
  Environment env;

  // @PropertySource를 통해 로딩된 프로퍼티 값 중에서 특정 값만 필드로 주입 받을 수 있다.
  // => 필드에 @Value 애노테이션을 붙인다.
  // => @Value("${프로퍼티이름}")
  @Value("${jdbc.url}")
  String jdbcUrl;

  @Value("${jdbc2.url}")
  String jdbc2Url;

  // @Value를 사용할 수 있다면 파라미터에도 적용할 수 있다.
  @Bean
  public Car car(//
      @Value("${jdbc.username}") String username, //
      @Value("${jdbc.password}") String password) {

    System.out.println("car() 호출 :");
    System.out.println("  " + username);
    System.out.println("  " + password);
    return new Car(null);
  }

}


