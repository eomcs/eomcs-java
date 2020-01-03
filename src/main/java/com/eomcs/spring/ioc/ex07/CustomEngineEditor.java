// 프로퍼티 에디터 만들기 
package com.eomcs.spring.ioc.ex07;

import java.beans.PropertyEditorSupport;
import java.sql.Date;

public class CustomEngineEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    System.out.println("CustomEngineEditor.setAsText()");
    String[] values = text.split(",");

    Engine engine = new Engine();
    engine.setMaker(values[0]);
    engine.setValve(Integer.parseInt(values[1]));
    engine.setCylinder(Integer.parseInt(values[2]));

    this.setValue(engine);
  }
}









