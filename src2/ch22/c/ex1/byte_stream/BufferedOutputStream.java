package ch22.c.ex1.byte_stream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedOutputStream extends FileOutputStream {
  
  byte[] buf = new byte[1024];
  int size = 0;
  
  public BufferedOutputStream(String name) throws FileNotFoundException {
    super(name);
  }

  @Override
  public void write(int b) throws IOException {
    buf[size++] = (byte)b;

    if (size >= buf.length) { // 배열이 꽉 찼으면 파일로 출력한다.
      write(buf);
      size = 0;
    }
  }
  
  @Override
  public void flush() throws IOException {
    if (size > 0) { // 배열에 출력할 데이터가 남아 있다면 파일로 출력한다.
      write(buf, 0, size);
      size = 0;
    }
    super.flush(); // 원래 수퍼 클래스에서 하던 일이 있다면 그대로 수행시킨다.
  }

}









