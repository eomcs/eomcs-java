// 변수 활용
package com.eomcs.study.lang.variable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("lang.variable.Exam4") // 클래스 이름이 같은 경우 충돌 방지
@RequestMapping("/lang/variable/exam4")
public class Exam4 {

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
    return "==> " + result;
  }
}







