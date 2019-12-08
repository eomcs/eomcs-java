package ch22.c.ex1.byte_stream;

import java.io.FileInputStream;
import java.io.IOException;

// 상속을 이용한 기능 추가
// => 기존의 FileInputStream에 버퍼링 기능을 추가하기
public class BufferedInputStream extends FileInputStream {
  
  byte[] buf = new byte[8192];
  int size = 0;
  int cursor = 0;
  int count = 0;
  
  public BufferedInputStream(String name) throws IOException {
    super(name);
  }
  
  @Override
  public int read() throws IOException {
    if (cursor >= size) { // 버퍼에 보관된 데이터를 다 읽었으면, 
      count++;
      size = read(buf); // 다시 상속 받은 메서드를 사용하여 8192 바이트를 읽어 온다.
      if (size == -1) // 물론 읽은 데이터가 없다면, 즉 파일의 데이터를 다 읽었다면 -1을 리턴한다.
        return -1;
      cursor = 0; // 새로 8192 바이트를 읽어 버퍼에 저장했다면,
    }             // 다시 커서의 위치를 0으로 설정한다.
    
    // 배열에 들어 있는 바이트의 값이 양수일 경우 int로 리턴하면 그 값 그대로 리턴된다.
    // 예) 0x7f(01001111, 10진수로 127) 값을 리턴한다면 
    //     4바이트 int 값 0x0000007f 가 리턴 된다.
    // 문제는 첫 비트가 1로 시작하는 바이트 값을 int로 리턴하는 경우이다.
    // JVM은 첫 비트가 1일 경우 음수 간주하고 
    // byte 값을 int로 바꿀 때 앞의 3바이트를 모두 음수를 의미하는 1로 설정한다.
    // 예) 0x80(10000000, 10진수로 128) 값을 리턴한다면
    //     4바이트 int 값 0xffffff80 이 리턴 된다.
    // 즉 바이트 배열에 들어 있는 값의 첫 비트가 1로 시작할 경우 이런 오류가 발생하는 것이다.
    // 원래의 바이트 값을 온전히 4바이트로 바꿔 리턴하고 싶다면,
    // 앞의 3바이트를 무조건 0으로 만들면 된다.
    // 즉 다음과 같이 4바이트로 변환된 값을 리턴하기 전에
    // 비트 연산자 &를 사용하여 앞의 3바이트를 0으로 설정하라!
    // 예) 0x80 --> 0xffffff80 & 0x000000ff --> 0x00000080
    
    // 현재 커서가 가리키는 위치의 버퍼 항목을 리턴한다.
    // 단 앞의 3바이트는 0으로 설정한 다음에 리턴한다.
    return buf[cursor++] & 0x000000ff; 
  }
  
}






