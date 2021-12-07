// 변수 활용
package com.eomcs.study.lang.variable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("lang.variable.Exam4") // 클래스 이름이 같은 경우 충돌 방지
@RequestMapping("/lang/variable/exam4")
public class Exam4 {

  // 테스트:
  // => /static/lang/literal/variable/exam4/test1.html
  @GetMapping("/test1")
  public String test1(int v1, int v2, String op) {
    int result = 0;
    switch (op) {
      case "+": result = v1 + v2; break;
      case "-": result = v1 - v2; break;
      case "*": result = v1 * v2; break;
      case "/": result = v1 / v2; break;
      case "%": result = v1 % v2; break;
      default: return "해당 연산을 수행할 수 없습니다.";
    }

    // 이렇게 서버에서 웹브라우저가 출력할 화면을 
    // HTML로 만들어 보내는 것을 
    // "서버측 렌더링(server-side rendering)"이라 부른다.
    //
    String html = "<!DOCTYPE html>"
        + "<html>"
        + "<head>"
        + "<meta charset=\"UTF-8\">"
        + "<title>변수 활용</title>"
        + "</head>"
        + "<body>"
        + "<h1>계산 결과</h1>"
        + "<p>" + v1 + " " + op + " " + v2 + " = " + result + "</p>"
        + "</body>" 
        + "</html>";

    return html;
  }

  // 테스트:
  // => /static/lang/literal/variable/exam4/test2.html
  @GetMapping("/test2")
  public Object test2(int v1, int v2, String op) {
    int result = 0;
    switch (op) {
      case "+": result = v1 + v2; break;
      case "-": result = v1 - v2; break;
      case "*": result = v1 * v2; break;
      case "/": result = v1 / v2; break;
      case "%": result = v1 % v2; break;
      default: return "해당 연산을 수행할 수 없습니다.";
    }

    return result;
  }

  // 테스트:
  // => /static/lang/literal/variable/exam4/test3.html
  @GetMapping("/test3")
  public Object test3() {
    String[] names = {"홍길동", "임꺽정", "유관순", "안중근", "윤봉길"};    
    return names; // 스프링부트는 이 메서드가 리턴한 배열을 JSON 형식의 문자열로 바꿔 응답한다.
  }

}







