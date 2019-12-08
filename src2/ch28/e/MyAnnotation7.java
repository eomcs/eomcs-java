package ch28.e;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyAnnotation7 {
  String[] value() default {"aaa", "bbb"};
  String name();
  int age();
  boolean working() default true;
}
