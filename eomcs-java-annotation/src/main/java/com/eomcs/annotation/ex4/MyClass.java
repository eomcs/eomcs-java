// 애노테이션 프로퍼티 값 지정하기 - 프로퍼티 이름 생략
package com.eomcs.annotation.ex4;

//@MyAnnotation(value="홍길동") // OK!
@MyAnnotation("홍길동") // OK! value 프로퍼티는 이름 생략 가능!
public class MyClass {
}
