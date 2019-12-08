// 애노테이션 프로퍼티
// => 메서드 선언하는 문법과 비슷하다.
// => 기본 값을 지정하지 않으면 필수 입력이다.
// => value 라는 이름을 갖는 프로퍼티의 경우 
//    값을 지정할 때 이름을 생략할 수 있다.
// 
package ch28.e;

public class Test02 {
  
  @MyAnnotation4(
      value="okok",
      name="홍길동",
      age=20,
      working=true)
  int a;
  
  // MyAnnotation4에 있는 4개의 프로퍼티 모두 필수 입력 항목이기 때문에 
  // 모든 항목의 값을 설정해야 한다.
  /*
  @MyAnnotation4(
      value="okok",
      name="홍길동",
      age=20)
  */
  int x;
  
  /*
  @MyAnnotation4(
      "okok", // value 외에 다른 속성의 값도 함께 설정할 경우 value 이름 생략 불가!
      name="홍길동",
      age=20,
      working=true)
  */
  int b;
  
  // value를 제외한 나머지 프로퍼티는 선택 입력 항목이기 때문에 값을 설정하지 않아도 된다.
  // => 설정하지 않으면 기본 값이 사용된다.
  @MyAnnotation5(
      value="okok")
  int y;
  
  
  @MyAnnotation5("okok") // value 프로퍼티 값만 설정할 경우에 이름을 생략할 수 있다.
  int c;
  
}






