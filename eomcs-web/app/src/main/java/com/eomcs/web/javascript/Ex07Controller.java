package com.eomcs.web.javascript;

import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Ex07Controller {

  @RequestMapping("/javascript/ex07/test1")
  public void test1() {
    // view url을 지정하지 않으면 요청 URL을 view url로 사용한다.
  }

  @RequestMapping("/javascript/ex07/test2")
  public String test2(HttpServletResponse response) {
    // 모든 클라이언트에 대해
    // 이 URL의 AJAX 요청을 허락한다는 의미다. 
    response.setHeader("Access-Control-Allow-Origin", "*");

    // HttpServletResponse를 사용한다면 view URL을 명시적으로 지정해야 한다.
    return "/javascript/ex07/test2";
  }
}
