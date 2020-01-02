// DataInputStream을 FileInputStream이나 ByteArrayInputStream에 붙일 수 있도록 
// 기능을 변경한다. 즉 DataInputStream을 장신구(decorator)로 만든다.
package com.eomcs.io.ex08;

import java.io.InputStream;

public class DataInputStream {

  InputStream in;

  public DataInputStream(InputStream in) {
    this.in = in;
  }

  public String readUTF() throws Exception {
    // 생성자에서 받은 입력 객체의 read() 메서드를 사용하여 문자열 출력
    int size = in.read(); 
    byte[] bytes = new byte[size];
    in.read(bytes); 
    return new String(bytes, "UTF-8");
  }

  public int readInt() throws Exception {
    // 생성자에서 받은 입력 객체의 read() 메서드를 사용하여 int 값 출력
    int value = 0;

    value = in.read() << 24;
    value += in.read() << 16;
    value += in.read() << 8;
    value += in.read();
    return value;
  }

  public long readLong() throws Exception {
    // 생성자에서 받은 입력 객체의 read() 메서드를 사용하여 long 값 출력
    long value = 0;
    value += (long)in.read() << 56;
    value += (long)in.read() << 48;
    value += (long)in.read() << 40;
    value += (long)in.read() << 32;
    value += (long)in.read() << 24;
    value += (long)in.read() << 16;
    value += (long)in.read() << 8;
    value += (long)in.read();
    return value;
  }

  public boolean readBoolean() throws Exception {
    // 생성자에서 받은 입력 객체의 read() 메서드를 사용하여 boolean 값 출력
    if (in.read()==1) 
      return true;
    else 
      return false;
  }

  public void close() throws Exception {
    in.close();
  }
}





