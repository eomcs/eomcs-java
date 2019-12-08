// Connection-Oriented vs Connectionless
package ch23.f;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server2 {
  public static void main(String[] args) {
    
    // Connectionless
    // => 서버와 연결없이 데이터를 보내고 받을 수 있다.
    // => DatagramSocket, DatagramPacket을 사용하여 처리한다.
    
    // 데이터 송수신을 담당할 소켓을 먼저 준비한다.
    // => 보내는 쪽이나 받는 쪽이나 같은 소켓 클래스를 사용한다.
    //    서버 소켓이 따로 없다.
    // => 받는 쪽에서는 소켓을 생성할 때 포트번호를 설정한다.
    try (DatagramSocket socket = new DatagramSocket(8888)) {
      
      // 데이터를 받을 버퍼를 준비한다.
      byte[] buf = new byte[8192];
      
      // 빈 패킷을 준비한다.
      DatagramPacket emptyPacket = new DatagramPacket(buf, buf.length);
      
      // 소켓을 이용하여 네트워크로부터 패킷을 받는다.
      // => 물론 받을 때까지 리턴하지 않는다.
      socket.receive(emptyPacket);
      System.out.println("데이터 수신 완료!");
      
      String message = new String(
          emptyPacket.getData(), // 패킷에서 바이트 배열을 꺼낸다.
          0, // 패킷에서 꺼낸 버퍼에서 0번째 부터 개수 만큼 바이트 배열을 읽어서 문자열로 만들 것이다.
          emptyPacket.getLength(), // 패킷에서 받은 바이트의 개수를 알아낸다.
          "UTF-8" // 바이트 배열로 인코딩된 문자표의 이름을 지정한다.
      );
      System.out.println(message);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}











