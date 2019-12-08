package ch28.c;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

// 애노테이션 사용 범위
// 
@Target({ElementType.LOCAL_VARIABLE,ElementType.METHOD}) // 로컬변수와 메서드 선언에 붙일 수 있다.
public @interface MyAnnotation5 {

}
