// 계산기 클라이언트 만들기 - 6단계: 서버의 인사말을 출력하기
package com.eomcs.net.ex11.v7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/*
 * 구현 조건: - 최소 +, -, *, /, % 연산자는 지원한다.
 * 
 * 실행 예: - 클라이언트가 계산기 서버에 접속한 후
 * 
 * 계산기 서버에 오신 걸 환영합니다! <== 서버의 응답 계산식을 입력하세요! <== 서버의 응답 예) 23 + 7 <== 서버의 응답 > 23 + 7 <== 사용자의 입력.
 * '>'문자는 클라이언트에서 출력한다. 결과는 30 입니다. <== 서버의 응답 > 23 ^ 7 <== 사용자의 입력. '>'문자는 클라이언트에서 출력한다. ^ 연산자를
 * 지원하지 않습니다. <== 서버의 응답 > ok + yes <== 사용자의 입력 식의 형식이 잘못되었습니다. <== 서버의 응답 > quit <== 사용자의 입력.
 * '>'문자는 클라이언트에서 출력한다. 안녕히 가세요! <== 서버의 응답
 * 
 */
public class CalculatorClient {
  public static void main(String[] args) {

    try (Scanner keyboard = new Scanner(System.in);
        Socket socket = new Socket("localhost", 8888);
        PrintStream out = new PrintStream(socket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

      // 서버에서 빈 문자열을 보낼 때까지 읽어서 화면에 출력한다.
      while (true) {
        String input = in.readLine();
        System.out.println(input);
        if (input.length() == 0)
          break;
      }

      while (true) {
        System.out.print("> ");
        String input = keyboard.nextLine();

        out.println(input);
        out.flush();

        String response = in.readLine();
        System.out.println(response);

        if (input.equalsIgnoreCase("quit"))
          break;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}


