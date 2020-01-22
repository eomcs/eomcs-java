// 예외 처리 후 마무리 작업 - finally 블록
package com.eomcs.exception.ex3;

import java.io.IOException;
import java.sql.SQLException;

public class Exam0520 {

  static void m(int i) throws Exception, RuntimeException, SQLException, IOException {
    if (i == 0)
      throw new Exception();
    else if (i == 1)
      throw new RuntimeException();
    else if (i == 2)
      throw new SQLException();
    else if (i == 3)
      throw new IOException();
  }

  public static void main(String[] args) throws Exception {
    try {
      m(1);
      // m()에서 발생된 예외는
      // try 블록에서 받지 않는다.
      // 따라서 main() 호출자에게 위임한다.
      // => 물론 main() 메서드 선언부에 위임할 예외의 종류를 표시해야 한다.
      //
    } finally {
      // try 블록을 나가기 전에 무조건 실행해야 할 작업이 있다면
      // catch 블록이 없어도 finally 블록만 사용할 수 있다.
      System.out.println("마무리 작업 실행!");
    }
  }

}
