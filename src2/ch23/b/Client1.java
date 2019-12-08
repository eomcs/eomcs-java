// 데이터 주고 받기 - 클라이언트 만들기
package ch23.b;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client1 {

  public static void main(String[] args) {
    
    try (Socket socket = new Socket("localhost", 8888);
        
        // 서버랑 데이터를 주고 받을 수 있도록 입출력 스트림 객체를 준비한다.
        // => 출력 스트림 객체를 준비하기
        OutputStream out = socket.getOutputStream();
        
        // => 입력 스트림 객체를 준비하기
        InputStream in = socket.getInputStream()) {
      
      System.out.println("서버와 연결되었음!");
      
      // 서버에게 데이터 보내기
      // - 보통 클라이언트 쪽에서 먼저 서버로 데이터를 보낸다.
      // - 서버에서 데이터를 모두 받아야만 리턴한다. 
      //   즉 blocking 모드로 작동한다.
      // - 실제 write()는 소켓의 내부 버퍼로 출력한다.
      //   따라서 write() 호출 후 즉시 리턴될 것이다.
      out.write(100);
      out.flush();
      System.out.println("서버에 데이터를 보냈음!");
      
      // 서버의 응답을 받는다.
      // - 서버가 응답을 할 때까지 리턴하지 않는다.
      //   즉 blocking 모드로 작동한다.
      int response = in.read();
      System.out.println(response);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}








