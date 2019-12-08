// 애노테이션 프로퍼티 추출하기
// 
package ch28.e;

import java.lang.annotation.Annotation;

@MyAnnotation7(
    name="홍길동",
    age=20)
public class Test04 {
  public static void main(String[] args) {
    
    Class<?> clazz = Test04.class;
    // 클래스에서 특정 애노테이션의 값을 추출하고 싶다면,
    //    getAnnotation(애노테이션 타입) 
    // => 리턴 값은 그 애노테이션의 값을 담고 있는 인스턴스이다.
    MyAnnotation7 anno1 = clazz.getAnnotation(MyAnnotation7.class);
    for (String s : anno1.value()) 
      System.out.println("==> " + s);
    System.out.println(anno1.name());
    System.out.println(anno1.age());
    System.out.println(anno1.working());
    System.out.println("--------------------------");
    
    // 위와 같다.
    MyAnnotation7 anno2 = Test04.class.getAnnotation(MyAnnotation7.class);
    for (String s : anno2.value()) 
      System.out.println("==> " + s);
    System.out.println(anno2.name());
    System.out.println(anno2.age());
    System.out.println(anno2.working());
    System.out.println("-----------------------------");
    
    Annotation[] annotations = Test04.class.getAnnotations();
    
    // 클래스에서 애노테이션을 배열을 받았을 경우 형변환해서 사용하면 
    // 애노테이션의 프로퍼티 값을 추출할 수 있다.
    MyAnnotation7 anno3 = (MyAnnotation7) annotations[0]; 
    for (String s : anno3.value()) 
      System.out.println("==> " + s);
    System.out.println(anno3.name());
    System.out.println(anno3.age());
    System.out.println(anno3.working());
    System.out.println("-----------------------------");
  }
}






