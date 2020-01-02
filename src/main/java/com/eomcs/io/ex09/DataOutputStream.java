// OutputStream에 붙이는 플러그인(decorator)을 다른 플러그인에 붙이려면,
// 플러그인(decorator) 클래스도 OutputStream의 가족이 되어야 한다.  
package com.eomcs.io.ex09;

import java.io.IOException;
import java.io.OutputStream;

// 이 상속은 기능을 상속 받기 위한 것이 아니다.
// 같은 그룹으로 묶이기 위함이다.
public class DataOutputStream extends OutputStream {

  OutputStream out;

  public DataOutputStream(OutputStream out) {
    this.out = out;
  }

  @Override
  public void write(int b) throws IOException {
    // 생성자에서 받은 출력 객체의 write() 메서드를 사용
    out.write(b);
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

  @Override
  public void close() throws IOException {
    // 생성자에서 받은 출력 객체의 close() 메서드를 사용
    out.close();
  }
}





