// Character Stream - 읽은 데이터를 문자 배열의 특정 위치에 저장하기
package com.eomcs.io.ex03;

import java.io.FileReader;
import java.nio.CharBuffer;

public class Exam0420 {

  public static void main(String[] args) throws Exception {
    FileReader in = new FileReader("temp/test2.txt");

    CharBuffer charBuf = CharBuffer.allocate(100);

    int count = in.read(charBuf);

    in.close();

    System.out.printf("%d\n", count);

    // read() 메서드가 파일에서 데이터를 읽어서 버퍼에 채울 때 마다 커서의 위치를 이동시킨다.
    // 버퍼의 데이터를 읽으려면 커서의 위치를 처음으로 되돌려야 한다.
    // 다음 flip() 메서드를 호출하여 버퍼의 커서를 처음으로 위치시킨다.
    charBuf.flip();

    System.out.printf("[%s]\n", charBuf.toString());
  }

}


