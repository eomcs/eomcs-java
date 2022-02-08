package com.eomcs.net.ex11.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class CalculatorServer {

  public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("서버 실행 중...");

      while (true) {
        try (
            Socket socket = serverSocket.accept();
            Scanner in = new Scanner(socket.getInputStream());
            PrintStream out = new PrintStream(socket.getOutputStream());
            ) {

          try (BufferedReader welcomeIn = new BufferedReader(new FileReader("welcome.txt"));) {
            while (true) {
              String str = welcomeIn.readLine();
              if (str == null) {
                break;
              }
              out.println(str);
            }
          } catch (Exception e) {
            out.println("환영합니다!");
          }

          out.println("계산식을 입력하세요!");
          out.println("예) 23 + 7 ");
          out.println();

          String str = in.nextLine();
          out.println(str);
          out.flush();

        } catch (Exception e) {
          System.out.println("클라이언트 요청 처리 중 오류 발생!");
        }
      }

    } catch (Exception e) {
      System.out.println("서버 소켓 생성 중 오류 발생!");
    }
  }
}
