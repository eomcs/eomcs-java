// stateful 방식 - 계산기 클라이언트 만들기
package com.eomcs.net.ex04;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

//stateful 방식
//=> 한 번 연결한 후 여러 번 요청/응답하는 방식
//=> 단점: 
//한 번 연결하면 클라이언트가 연결을 끊을 때까지 계속 유지한다.
//클라이언트가 작업을 요청하지 않더라도 계속 연결정보를 유지해야 하기 때문에 
//메모리를 많이 차지하고 동시에 많은 클라이언트의 요청을 처리하기 힘들다.
//=> 장점:
//한 번 서버에 연결되면 클라이언트가 연결을 끊을 때까지 유지되기 때문에
//작업을 처리하는 속도가 빠르다.
//작업처리하는데 걸리는 시간 = 데이터 전송 시간 + 작업 처리 시간 + 데이터 수신 시간
//즉 작업을 요청할 때마다 연결할 필요가 없기 때문에 연결하는데 시간이 걸리지 않는다. 
//
//=> 대표적인 예:
//게임 서버 연결 - 서버에 한 번 연결되면 게임을 마칠 때까지 데이터를 주고 받는다.
//화상 통신 - 한 번 연결하면 연결을 끊을 때까지 데이터를 주고 받는다.
//채팅 서버 - 전용 클라이언트를 이용한 채팅
//텔렛 - 원격 제어 프로그램
//FTP - 파일 전송 프로그램
//
public class CalcClient2 {
  public static void main(String[] args) throws Exception {
    Scanner keyScan = new Scanner(System.in);

    Socket socket = new Socket("localhost", 8888);
    Scanner in = new Scanner(socket.getInputStream());
    DataOutputStream out = new DataOutputStream(socket.getOutputStream());

    while (true) {
      System.out.print("값1? ");
      out.writeInt(Integer.parseInt(keyScan.nextLine()));

      System.out.print("연산자? ");
      out.writeUTF(keyScan.nextLine());

      System.out.print("값2? ");
      out.writeInt(Integer.parseInt(keyScan.nextLine()));

      String str = in.nextLine();
      System.out.println(str);

      if (str.equals("quit"))
        break;
    }

    in.close();
    out.close();
    socket.close();
  }
}





