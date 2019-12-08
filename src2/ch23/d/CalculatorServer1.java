// Stateful 서버 만들기
package ch23.d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculatorServer1 {
  public static void main(String[] args) {
    
    try (ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("서버 실행 중...");
      
      // 서버의 Stateful 통신 방법
      // => 먼저 클라이언트와 연결된 후 클라이언트 요청을 처리한다.
      // => 클라이언트에서 연결을 끊기 전까지는 계속 연결을 유지한다.
      //
      while (true) {
        
        try (Socket socket = serverSocket.accept();
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            PrintStream out = new PrintStream(socket.getOutputStream());) {
          
          System.out.println("클라이언트와 연결됨!");
          
          while (true) {
            String request = in.readLine();
            System.out.println("요청 처리함.");
            
            if (request.equalsIgnoreCase("quit")) {
              out.println("종료합니다!");
              out.flush();
              break;
            }
            
            String[] input = request.split(" ");
            
            int a = 0, b = 0, result = 0;
            String op = null;
             
            try { 
              a = Integer.parseInt(input[0]);
              op = input[1];
              b = Integer.parseInt(input[2]);
            } catch (Exception e) {
              out.println("식의 형식이 바르지 않습니다.");
              out.flush();
              continue;
            }
            
            switch (op) {
              case "+": result = a + b; break;
              case "-": result = a - b; break;
              case "*": result = a * b; break;
              case "/": result = a / b; break;
              case "%": result = a % b; break;
              default:
                out.printf("%s 연산자를 지원하지 않습니다.\n", op);
                out.flush();
                continue;
            }
            
            out.printf("결과는 %d 입니다.\n", result);
            out.flush();
            
          } // while
          
        } catch (Exception e) {
          // 클라이언트 요청을 처리하다가 예외가 발생하면 무시하고 연결을 끊는다.
          System.out.println("클라이언트와 통신 중 오류 발생!");
        }
        
        System.out.println("클라이언트와 연결 끊음!");
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
