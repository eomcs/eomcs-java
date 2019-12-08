package ch22.c.ex4.byte_stream;

import java.io.IOException;
import java.io.OutputStream;

public abstract class DecoratorOutputStream extends OutputStream {
  
  // 데코레이터 역할을 수행하는 객체는 항상 다른 OutputStream을 포함해야 한다.
  // 즉 다른 OutputStream 객체에 기능을 붙일 것이기 때문이다.
  protected OutputStream other;
  
  public DecoratorOutputStream(OutputStream other) {
    this.other = other;
  }
  
  @Override
  public void flush() throws IOException {
    other.flush();
  }
}
