// 애노테이션 프로퍼티 - value 프로퍼티
package com.eomcs.annotation.ex4;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String value();
}








