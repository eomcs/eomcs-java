package ch28.e;

// 선택 프로퍼티
// => 애노테이션을 사용할 때 값을 설정하지 않아도 된다.
// => 값을 설정하지 않으면 기본 값이 사용된다.
public @interface MyAnnotation3 {
  String value() default "오호라"; // 선택 입력
}
