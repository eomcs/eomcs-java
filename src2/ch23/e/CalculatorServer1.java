// Stateful 응용 - 클라이언트의 계산 결과를 서버에 유지하기
package ch23.e;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculatorServer1 {
  public static void main(String[] args) {
    
    try (ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("서버 실행 중...");
      
      // 서버의 Stateful 응용
      // => 작업 결과를 변수에 저장하여 연결을 끊을 때까지 계속 유지한다.
      //
      while (true) {
        
        try (Socket socket = serverSocket.accept();
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            PrintStream out = new PrintStream(socket.getOutputStream());) {
          
          System.out.println("클라이언트와 연결됨!");
          
          // 클라이언트의 요청에 대한 계산 결과를 유지할 변수 선언
          int result = 0;
          
          while (true) {
            String request = in.readLine();
            System.out.println("요청 처리함.");
            
            if (request.equalsIgnoreCase("quit")) {
              out.printf("최종 계산 결과는 %d 입니다.\n", result);
              out.flush();
              break;
            }
            
            String[] input = request.split(" ");
            
            int b = 0;
            String op = null;
             
            try { 
              op = input[0];
              b = Integer.parseInt(input[1]);
            } catch (Exception e) {
              out.println("식의 형식이 바르지 않습니다.");
              out.flush();
              continue;
            }
            
            switch (op) {
              case "+": result += b; break;
              case "-": result -= b; break;
              case "*": result *= b; break;
              case "/": result /= b; break;
              case "%": result %= b; break;
              default:
                out.printf("%s 연산자를 지원하지 않습니다.\n", op);
                out.flush();
                continue;
            }
            
            out.printf("현재까지 계산 결과는 %d 입니다.\n", result);
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
