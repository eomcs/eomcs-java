package ch22.c.ex1.character_stream;

import java.io.FileReader;
import java.io.IOException;

// 상속을 이용한 기능 추가
// => 기존의 FileReader에 버퍼링 기능을 추가하기
public class BufferedReader extends FileReader {
  
  char[] buf = new char[8192];
  int size = 0;
  int cursor = 0;
  int count = 0;
  
  public BufferedReader(String name) throws IOException {
    super(name);
  }
  
  @Override
  public int read() throws IOException {
    if (cursor >= size) { 
      count++;
      size = read(buf); 
      if (size == -1) 
        return -1;
      cursor = 0; 
    }             
    
    return buf[cursor++]; 
  }
  
}






