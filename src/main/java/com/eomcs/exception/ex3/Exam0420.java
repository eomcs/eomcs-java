// 던지는 예외 받기 - 예외 처리 책임을 상위 호출자에게 위임
package com.eomcs.exception.ex3;

import java.io.IOException;
import java.sql.SQLException;

public class Exam0420 {

  static void m(int i) 
      throws Exception, RuntimeException, SQLException, IOException {
    if (i == 0)
      throw new Exception();
    else if (i == 1)
      throw new RuntimeException();
    else if (i == 2) 
      throw new SQLException();
    else 
      throw new IOException();
  }

  public static void main(String[] args) throws Exception {
    // 예외를 처리하고 싶지 않다면 이전 호출자에게 책임을 떠넘길 수 있다.
    // => main() 호출자는 JVM이고,
    //    JVM은 main() 예외를 던지는 순간 즉시 실행을 멈춘다.
    //    그래서 main()의 호출자에게 책임을 떠넘기는 것은 바람직하지 않다.
    // => 즉 main()은 예외 처리의 마지막 보루이다.
    m(1);
  }

}
