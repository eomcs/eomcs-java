package ch22.c.ex4.byte_stream;

import java.io.InputStream;

public abstract class DecoratorInputStream extends InputStream {
  
  // 데코레이터 역할을 수행하는 객체는 항상 다른 InputStream을 포함해야 한다.
  // 즉 다른 InputStream 객체에 기능을 붙일 것이기 때문이다.
  protected InputStream other;
  
  public DecoratorInputStream(InputStream other) {
    this.other = other;
  }
}
