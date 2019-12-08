// String 읽기 - CharBuffer 사용하여 읽기
package ch22.d;

import java.io.FileReader;
import java.nio.CharBuffer;

public class Test01_3 {

  public static void main(String[] args) throws Exception {

    // FileReader
    // => 문자 단위로 데이터를 읽는 일을 한다.
    //
    FileReader in = new FileReader("data.txt");

    // FileReader 객체가 읽은 데이터를 저장할 메모리를 준비한다.
    CharBuffer cbuf = CharBuffer.allocate(1024);
    
    // 읽은 데이터를 CharBuffer 에 저장한다.
    in.read(cbuf);
    
    // 버퍼의 데이터를 꺼내기 전에 읽은 위치를 0으로 초기화시킨다.
    cbuf.flip(); 
    
    System.out.println(cbuf); // 버퍼의 데이터를 읽어 출력한다.

    in.close();
    
    System.out.println("읽기 완료!");
  }

}
