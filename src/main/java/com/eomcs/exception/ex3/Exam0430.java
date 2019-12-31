// 던지는 예외 받기 - try ~ catch ~
package com.eomcs.exception.ex3;

import java.io.IOException;
import java.sql.SQLException;

public class Exam0430 {

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

  public static void main(String[] args) {
    try {
      // try 블록에서 예외가 발생할 수 있는 메서드를 호출한다.
      m(1);

    } catch (IOException e) {
      // catch 블록에서 그 예외를 받아서 처리한다.
      // 메서드가 던지는 예외 개수 만큼 catch 블록을 선언하면 된다.

    } catch (SQLException e) {

    } catch (RuntimeException e) {

    } catch (Exception e) {

    }
  }

}
