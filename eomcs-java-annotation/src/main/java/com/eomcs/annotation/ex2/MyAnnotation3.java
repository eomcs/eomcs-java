// 애노테이션 유지 정책
// => SOURCE  : 소스 파일에만 남긴다. 컴파일 후 제거된다.
// => CLASS   : .class 파일에 남긴다. 그러나 실행 시에 추출할 순 없다.(기본)
// => RUMTIME : .class 파일에 남긴다. 실행 시에 추출할 수 있다.
package com.eomcs.annotation.ex2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// 애노테이션 유지 정책을 RUNTIME라고 지정하면 
// 해당 애노테이션은 .class 파일에도 남아 있고 
// 실행 중에 추출할 수 있다.
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation3 {
    String value();
}








