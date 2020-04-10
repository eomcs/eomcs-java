// AOP(Aspect-Oriented Programming) - AOP 필터 적용 후
package com.eomcs.spring.ioc.ex13.b;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.eomcs.spring.ioc.SpringUtils;

// AOP?
// => 기존의 코드를 손대지 않고 특정 기능을 삽입하는 기술
// => 메서드 호출 앞뒤로 코드를 삽입할 수 있다.
// => 일종의 필터를 추가하는 기술이다.
//
// AOP 적용 방법
// => AOP 관련 의존 라이브러리 가져온다.
// - mvnrepository.com 에서 "aspectjweaver"로 검색한다.
// - build.gradle 에 등록
// - "gradle eclipse" 실행
// - 프로젝트 리프래시.
// => 필터 클래스를 정의한다.
// => XML 또는 자바 코드로 필터를 설정한다.
//
// AOP 용어:
// [Advice]
// => 메서드(join point) 호출 앞뒤에 삽입될 코드를 담고 있는 필터 객체이다.
// 예) MyAdvice 클래스
//
// [Join Point]
// => Advice가 삽입될 메서드이다.
// 예) m2() 메서드
//
// [Pointcut]
// => Advice를 삽입할 위치 정보이다.
// 예) execution(* com.eomcs.spring.ioc.ex13.b.*.*(..))
//
// [Target Object]
// => Advice를 삽입할 대상 객체이다.
// 예) Y, Z 클래스
//
// [Aspect]
// => 어느 pointcut 에 어떤 advice를 삽입할 것인지 가리키는 정보이다.
// 예) 설정 정보
//
public class Exam01 {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "com/eomcs/spring/ioc/ex13/b/application-context-01.xml");

    SpringUtils.printBeanList(iocContainer);

    X x = iocContainer.getBean(X.class);
    x.m1();
    System.out.println();

    x.m2();
    System.out.println();

    x.m3();
  }

}


