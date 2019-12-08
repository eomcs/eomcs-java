// Stateful 클라이언트 만들기
package ch23.d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/*
Stateful 통신 방법
=> 서버에 연결한 후, 클라이언트에서 연결을 끊을 때까지 계속해서 연결을 유지하며 클라이언트 요청을 처리한다.
=> 단점   
   - 클라이언트와 서버의 연결을 계속 유지하기 때문에 클라이언트에서 요청을 하지 않아도 
     서버쪽에는 클라이언트 정보를 계속 갖고 있어야 함으로 메모리 낭비가 있다.
   - 클라이언트와 연결되는 개수의 한계가 있기 때문에 
     보다 많은 크라이언트 요청을 처리할 수 없다.
=> 장점
   - 클라이언트와 서버는 계속 연결된 상태이기 때문에 
     요청할 때마다 연결하지 않기 때문에 시간 소요가 stateless 방식 보다 덜하다.
   - 클라이언트 정보를 계속 유지할 수 있다.
=> 예
   - telnet/ssh, ftp/sftp 등
 */
public class CalculatorClient1 {
  public static void main(String[] args) {
    
    Scanner keyboard = new Scanner(System.in);

    // 서버에 먼저 연결한다.
    // 연결을 끊을 때까지 요청/응답을 반복할 수 있다.
    // => 매번 요청할 때마다 서버와 연결할 필요가 없기 때문에 stateless 방식에 비해 
    //    요청처리 시간이 적게 걸린다.
    // => 대신 서버쪽에는 클라이언트 연결 정보를 계속 유지해야 하기 때문에 
    //    메모리를 일정부분 지속적으로 점유한다.
    // 
    try (Socket socket = new Socket("localhost", 8888);
        PrintStream out = new PrintStream(socket.getOutputStream());
        BufferedReader in = new BufferedReader(
            new InputStreamReader(socket.getInputStream()))) {
    
      System.out.println("서버와 연결됨!");

      while (true) {
        System.out.print("> ");
        String input = keyboard.nextLine();

        out.println(input);
        out.flush();
        
        System.out.println("서버에게 계산 작업을 요청함!");

        String response = in.readLine();
        System.out.println(response);

        if (input.equalsIgnoreCase("quit"))
          break;
      } // while
    
      System.out.println("서버와 연결 끊음!");

    } catch (Exception e) {
      e.printStackTrace();
    }
    
    keyboard.close();
  }
}











