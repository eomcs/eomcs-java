package ch30.g;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan("ch30.g")

// AOP 활성화시키기
// => XML에서는 <aop:aspectj-autoproxy/> 태그를 선언하였다.
@EnableAspectJAutoProxy

public class AppConfig {
}
