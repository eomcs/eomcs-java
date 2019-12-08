// 애노테이션 유지 범위 
// => CLASS
//    - .class 파일까지는 유지된다. 
//    - 그러나 runtime에는 메모리에 로딩되지 않는다.
//    - 애노테이션을 정의할 때 유지 범위를 지정하지 않으면 기본이 CLASS 이다.
// => SOURCE
//    - 컴파일 할 때 제거된다. 
//    - .class 파일에 포함되지 않는다.
//    - 보통 소스 파일에서 애노테이션 값을 꺼내 다른 파일을 자동 생성하는 도구를 만들 때 많이 사용한다.
// => RUNTIME
//    - .class 파일까지 유지되고, runtime에 메모리에 로딩된다.
//    - 실행 중에 애노테이션을 참조해야 할 경우에 많이 사용한다.
// 
package ch28.b;

import java.lang.annotation.Annotation;

@MyAnnotation // .class 파일에서 확인할 수 있다.
@MyAnnotation2 // .class 파일에 없다.
@MyAnnotation3 // .class 파일에서 확인할 수 있다.
public class Test01 {
  public static void main(String[] args) {
    // Test01.class 파일을 편집기로 열어서 확인해보라!
    
    // 실행할 때 추출 여부 확인하기
    // => 애노테이션 추출하기
    Class<?> clazz = Test01.class;
    Annotation[] annotations = clazz.getAnnotations();
    
    // MyAnnotation은 유지 범위가 CLASS 이기 때문에 실행할 때 메모리에 로딩되지 않는다.
    // 따라서 실행 중에 애노테이션 정보를 추출할 수 없다.
    // 그러나 MyAnnotation3는 유지 범위가 RUNTIME 이기 때문에 실행할 때 메모리에 로딩된다.
    // 따라서 실행 중에 애노테이션 정보를 추출할 수 있다.
    for (Annotation a : annotations) {
      System.out.println(a.annotationType().getName());
    }
    
  }
}






