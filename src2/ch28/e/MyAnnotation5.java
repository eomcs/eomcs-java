package ch28.e;

public @interface MyAnnotation5 {
  String value();
  String name() default "홍길동";
  int age() default 20;
  boolean working() default true;
}
