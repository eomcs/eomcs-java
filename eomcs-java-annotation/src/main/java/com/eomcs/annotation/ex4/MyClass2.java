// 애노테이션 프로퍼티 값 지정하기 - 프로퍼티 이름 생략
package com.eomcs.annotation.ex4;

//@MyAnnotation2(tel="222-2222") // OK!
@MyAnnotation2("222-2222") // value 속성이 아닌 경우 생략 불가!
public class MyClass2 {
}
