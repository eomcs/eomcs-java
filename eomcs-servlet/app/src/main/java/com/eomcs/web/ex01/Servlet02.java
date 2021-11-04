// 서블릿 만들기 - javax.servlet.GenericServlet 추상 클래스 상속
package com.eomcs.web.ex01;

import java.io.IOException;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

// GenericServlet 추상 클래스
// => javax.servlet.Servlet 인터페이스를 구현하였다.
// => service() 메서드만 남겨두고 나머지 메서드들은 모두 구현하였다.
// => 따라서 이 클래스를 상속 받는 서브 클래스는 service() 만 구현하면 된다.
//
public class Servlet02 extends GenericServlet {

  // GenericServlet 추상 클래스는 java.io.Serialize 인터페이스를 구현하였다.
  // => serialVersionUID 변수 값을 설정해야 한다.
  private static final long serialVersionUID = 1L;

  public Servlet02() {
    System.out.println("Servlet02()");
  }

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    System.out.println("Servlet02.service(ServletRequest,ServletResponse)");
  }
}


