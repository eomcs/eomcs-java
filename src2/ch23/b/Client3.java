// 데이터 주고 받기 - 파일 보내는 클라이언트 만들기
package ch23.b;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client3 {

  public static void main(String[] args) {
    
    try (Socket socket = new Socket("192.168.0.24", 8888);
        DataOutputStream out = new DataOutputStream( 
            new BufferedOutputStream(socket.getOutputStream()));
        Scanner in = new Scanner(socket.getInputStream())) {
      
      System.out.println("서버와 연결되었음!");
      
      File file = new File("temp/test.jpeg");
      try (BufferedInputStream fileIn = new BufferedInputStream(
            new FileInputStream(file))) {
        
        // 서버에 보낼 파일의 크기와 이름을 먼저 알려준다.
        long fileLen = file.length();
        out.writeLong(fileLen);
        out.writeUTF("okok.jpeg");
        out.flush();
        
        // 파일에서 1바이트를 읽어서 서버에 보낸다.
        // => 읽을 때도 버퍼를 사용하고 출력에도 버퍼를 사용하기 때문에 
        //    1바이트 씩 읽을 때 마다 서버에 보내는 것이 아니다.
        // => 버퍼가 꽉 차면 자동으로 서버에 보낸다.
        // 
        System.out.println("서버에 데이터를 보내는 중...");
        for (long i = 0; i < fileLen; i++) {
          out.write(fileIn.read()); // 서버로 바로 출력하는 것이 아니라 버퍼로 출력한다.
          // 버퍼가 다 차면 자동으로 방출된다.
        }
        // 버퍼에 남은 잔여 데이터를 보내는 것을 잊지 말라!
        out.flush();
        System.out.println("서버에 데이터를 보냈음!");
      }
      
      System.out.println("서버의 응답을 기다리고 있음!");
      String response = in.nextLine();
      System.out.println(response);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}








