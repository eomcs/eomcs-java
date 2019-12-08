// 애노테이션 프로퍼티 - 프로퍼티 생략
package com.eomcs.annotation.ex4;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation3 {
    String value();
    String tel(); 
}








