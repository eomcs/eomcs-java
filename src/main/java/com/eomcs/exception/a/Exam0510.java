// 예외 처리와 자원 해제 보장 - finally 블록
package com.eomcs.exception.a;

import java.util.Scanner;

public class Exam0510 {

  public static void main(String[] args) {

    Scanner keyboardScanner = null;

    try {
      keyboardScanner = new Scanner(System.in);

      System.out.print("이름? ");
      String name = keyboardScanner.nextLine();

      System.out.print("국어? ");
      int kor = keyboardScanner.nextInt();

      System.out.print("영어? ");
      int eng = keyboardScanner.nextInt();

      System.out.print("수학? ");
      int math = keyboardScanner.nextInt();

      int sum = kor + eng + math;
      float aver = sum / 3f;

      System.out.printf("%s : 합계 = %d, 평균 = %f\n",
          name, sum, aver);

    } catch (Throwable e) {
      System.out.println("입력 값이 옳지 않습니다.");

    } finally {
      // try {} 블록의 코드 실행을 성공적으로 완료한 후,
      // 또는
      // 예외가 발생하여 catch {} 블록을 실행한 후,
      // try ~ catch ~ 문을 나가기 전에
      // finally {} 블록은 반드시 실행된다.
      //
      // 그래서 finally {} 블록에는 보통
      // 사용 완료한 자원을 해제시키는 코드를 둔다.
      //
      if (keyboardScanner != null) {
        keyboardScanner.close();
      }
      System.out.println("키보드 입력 자원을 해제하였습니다.");
    }

  }
}

