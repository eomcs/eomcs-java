package ch28.e;

public @interface MyAnnotation2 {
  String value2(); // 필수 프로퍼티. 
  // 프로퍼티 이름이 value 아니라면,
  // 값을 설정할 때 이름을 생략할 수 없다.
}
