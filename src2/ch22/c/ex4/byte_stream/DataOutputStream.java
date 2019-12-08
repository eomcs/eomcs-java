package ch22.c.ex4.byte_stream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

//데코레이터 설계 패턴을 적용하여 기존 OutputStream에 기능을 추가한다.
//=> DecoratorOutputStream을 상속 받는다.
public class DataOutputStream extends DecoratorOutputStream {

  public DataOutputStream(OutputStream other) throws FileNotFoundException {
    super(other);
  }
  
  // 자신에게 1바이트 출력하라고 요청이 들어오면 
  // 생성자에서 받은 다른 OutputStream 객체에 일을 떠 넘긴다.
  // 왜? 데코레이터(악세서리)가 하는 일이 그렇다.
  // 
  @Override
  public void write(int b) throws IOException {
    other.write(b);
  }
  
  public void writeInt(int value) throws IOException {
    write(value >> 24);
    write(value >> 16);
    write(value >> 8);
    write(value);
  }
  
  public void writeShort(int value) throws IOException {
    write(value >> 8);
    write(value);
  }

  public void writeLong(long value) throws IOException {
    write((int)(value >> 56));
    write((int)(value >> 48));
    write((int)(value >> 40));
    write((int)(value >> 32));
    write((int)(value >> 24));
    write((int)(value >> 16));
    write((int)(value >> 8));
    write((int)(value));
  }
  
  public void writeUTF(String str) throws IOException {
    
    // String 인스턴스에 보관된 문자열을 바이트 배열로 꺼낸다.
    byte[] bytes = str.getBytes("UTF-8");
    
    // 먼저 출력할 바이트의 개수를 쓴다. 
    // 문자열을 출력할 때 한 번에 32767 바이트만 출력하게 한다. 
    writeShort(bytes.length);
    
    // 그리고 문자열의 UTF-8 코드 값이 들어 있는 바이트 배열을 출력한다.
    write(bytes);
  }
  
  public void writeBoolean(boolean value) throws IOException {
    if (value) {
      write(1);
    } else {
      write(0);
    }
  }
}









