// Stateless 응용 - 계산 결과 유지하기
package ch23.e;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/*
Stateless는 응답을 받은 후에 연결을 끊는다.
다시 요청할 때는 서버와 연결을 다시 한다.
서버쪽에서는 어떻게 클라이언트를 구분하여 작업 결과를 유지할 것인가? 
 */
public class CalculatorClient2 {
  public static void main(String[] args) {
    
    Scanner keyboard = new Scanner(System.in);

    // 서버에서 클라이언트를 구분할 때 사용할 값
    long sessionId = 0;
    
    while (true) {
      // 계산 요청을 할 때 연산자와 값만 넘긴다.
      // 예) + 2
      // 예) / 3
      //
      System.out.print("> ");
      String input = keyboard.nextLine();
      if (input.equalsIgnoreCase("quit"))
        break;

      try (Socket socket = new Socket("localhost", 8888);
          PrintStream out = new PrintStream(socket.getOutputStream());
          BufferedReader in = new BufferedReader(
              new InputStreamReader(socket.getInputStream()))) {
        
        System.out.println("서버와 연결됨! 서버에게 계산 작업을 요청함!");
        
        out.println(sessionId); // 서버에 먼저 세션 ID를 보낸다.
        out.println(input); // 사용자가 입력한 값을 보낸다.
        out.flush();

        if (sessionId == 0) {
          // 서버에 보낸 세션 ID가 0이면 서버는 새로 세션 ID를 발급하여 보내줄 것이다.
          // 받아야 한다.
          sessionId = Long.parseLong(in.readLine());
          System.out.printf("발급받은 세션 ID: %d\n", sessionId);
        }
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











