package ch29.g;

import java.beans.PropertyEditorSupport;

public class MyCustomBlackBoxEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    String[] values = text.split(",");
    
    BlackBox blackBox = new BlackBox();
    blackBox.setMaker(values[0]);
    blackBox.setModel(values[1]);
    
    this.setValue(blackBox);
  }
}
