package ch22.c.ex3.byte_stream;

import java.io.FileNotFoundException;
import java.io.IOException;
import ch22.c.ex1.byte_stream.BufferedOutputStream;

// 버퍼 기능을 추가하기 위해 기존에 작성한 BufferedOutputStream을 상속 받는다.
public class BufferedDataOutputStream extends BufferedOutputStream {

  public BufferedDataOutputStream(String name) throws FileNotFoundException {
    super(name);
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
    for (int i = 0; i < bytes.length; i++) {
      write(bytes[i]);
    }
  }
  
  public void writeBoolean(boolean value) throws IOException {
    if (value) {
      write(1);
    } else {
      write(0);
    }
  }
}









