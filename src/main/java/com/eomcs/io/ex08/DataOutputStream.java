package com.eomcs.io.ex08;

import java.io.OutputStream;

public class DataOutputStream {

  OutputStream out;

  public DataOutputStream(OutputStream out) {
    this.out = out;
  }

  public void writeUTF(String str) throws Exception {
    // 생성자에서 받은 출력 객체의 write() 메서드를 사용하여 문자열 출력
    byte[] bytes = str.getBytes("UTF-8");
    out.write(bytes.length);
    out.write(bytes);
  }

  public void writeInt(int value) throws Exception {
    // 생성자에서 받은 출력 객체의 write() 메서드를 사용하여 int 값 출력
    out.write(value >> 24);
    out.write(value >> 16);
    out.write(value >> 8);
    out.write(value);
  }

  public void writeLong(long value) throws Exception {
    // 생성자에서 받은 출력 객체의 write() 메서드를 사용하여 long 값 출력
    out.write((int)(value >> 56));
    out.write((int)(value >> 48));
    out.write((int)(value >> 40));
    out.write((int)(value >> 32));
    out.write((int)(value >> 24));
    out.write((int)(value >> 16));
    out.write((int)(value >> 8));
    out.write((int)value);
  }

  public void writeBoolean(boolean value) throws Exception {
    // 생성자에서 받은 출력 객체의 write() 메서드를 사용하여 boolean 값 출력
    if (value) 
      out.write(1);
    else 
      out.write(0);
  }

  public void close() throws Exception {
    out.close();
  }
}





