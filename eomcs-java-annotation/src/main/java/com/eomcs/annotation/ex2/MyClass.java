// 애노테이션 사용
// => @애노테이션이름(프로퍼티명=값, 프로퍼티명=값, ...)
//
package com.eomcs.annotation.ex2;

@MyAnnotation(value="값") // 유지정책 => CLASS 
@MyAnnotation2(value="값") // 유지정책 => SOURCE 
@MyAnnotation3(value="값") // 유지정책 => RUNTIME 
public class MyClass {
}
