// 스프링 IoC 컨테이너를 설정하는 클래스
package com.eomcs.spring.ioc.ex13.f;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan("com.eomcs.spring.ioc.ex13.f")
@EnableAspectJAutoProxy
public class AppConfig {
}
