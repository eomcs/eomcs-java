package ch29.g;

import java.beans.PropertyEditorSupport;
import java.sql.Date;

// 스프링 IoC 컨테이너에서 사용할 프로퍼티 에디터 만들기
// => java.beans.PropertyEditor 인터페이스를 구현하면 된다.
//    문제는 인터페이스에 선언된 메서드가 너무 많아 클래스로 구현하기가 매우 번거롭다.
// => java.beans.PropertyEditorSupport 클래스를 상속 받으면 된다.
//    PropertyEditor 인터페이스를 구현한 클래스이다.
//    이 클래스를 상속 받아 필요한 메서드만 오버라이딩 하는 것이 
//    인터페이스를 직접 구현하는 것 보다 편하다!
// 
public class MyCustomDateEditor extends PropertyEditorSupport {
  
  // 스프링 IoC 컨테이너는 String을 java.sql.Date 클래스로 바꾸기 위해 이 메서드를 먼저 호출한다.
  // 그리고 getValue()를 호출하여 변환된 값을 꺼내 쓴다.
  //@Override
  public void setAsText(String text) throws IllegalArgumentException {
    System.out.printf("MyCustomDateEditor.setAsText(%s)\n", text);
    // text 파라미터는 XML에 설정한 String 값이다. 
    // 이 값을 분석하여 우리가 원하는 java.sql.Date 객체로 바꾸면 된다.
    Date date = Date.valueOf(text);
    
    // String 값을 java.sql.Date 객체로 바꾼 다음에 
    // 내부에 보관한다.
    // => 이렇게 setValue()로 보관된 값을 
    //    스프링 IoC 컨테이너는 getValue()를 호출하여 꺼내 쓴다.
    this.setValue(date);
  }
  
  @Override
  public Object getValue() {
    System.out.println("MyCustomDateEditor.getValue()");
    return super.getValue();
  }

}









