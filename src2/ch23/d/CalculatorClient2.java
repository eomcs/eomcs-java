// Stateless 클라이언트 만들기
package ch23.d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/*
Stateless 통신 방법
=> 서버에 연결한 후, 한 번 요청하고 응답 받은 후 연결을 끊는다.
=> 단점   
   - 서버에 요청할 때마다 연결해야 한다.
   - 지속적으로 서버에 연결하는데 실행 시간이 소요된다.
=> 장점
   - 서버에 계속 연결된 상태가 아니기 때문에 서버쪽에서 메모리를 많이 사용하지 않는다.
   - stateful 보다 더 많은 클라이언트 요청을 처리할 수 있다.
=> 예
   - HTTP 프로토콜, 메신저 등 
 */
public class CalculatorClient2 {
  public static void main(String[] args) {
    
    Scanner keyboard = new Scanner(System.in);
    
    while (true) {
      System.out.print("> ");
      String input = keyboard.nextLine();
      if (input.equalsIgnoreCase("quit"))
        break;

      // 서버에 요청할 때 연결을 하고
      // 서버로부터 응답을 받으면 연결을 끊는다.
      // => 매번 요청할 때마다 서버와 연결해야 하기 때문에 실행 시간 중에 연결에 소요되는 시간이 
      //    일정하게 걸린다.
      // => 대신 서버로부터 응답을 받은 후에 즉시 연결을 끊기 때문에 
      //    서버쪽에는 메모리가 낭비되지 않는다.
      try (Socket socket = new Socket("localhost", 8888);
          PrintStream out = new PrintStream(socket.getOutputStream());
          BufferedReader in = new BufferedReader(
              new InputStreamReader(socket.getInputStream()))) {
        
        System.out.println("서버와 연결됨! 서버에게 계산 작업을 요청함!");
        
        out.println(input);
        out.flush();

        String response = in.readLine();
        System.out.println(response);

      } catch (Exception e) {
        e.printStackTrace();
      }
      System.out.println("서버와 연결 끊음!");
      
    } // while
    
    keyboard.close();
  }
}











