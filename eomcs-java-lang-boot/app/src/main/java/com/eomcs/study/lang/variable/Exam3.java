// 변수 : 스프링 부트의 자동 형변환
package com.eomcs.study.lang.variable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("lang.variable.Exam3") // 클래스 이름이 같은 경우 충돌 방지
@RequestMapping("/lang/variable/exam3")
public class Exam3 {

  //스프링 부트는 클라이언트가 보낸 값을 파라미터 타입에 맞춰 자동 형변환을 수행한다.
  //=> 웹브라우저가 보내는 값은 무조건 "문자열"이다.

  // 1) "100" ==> byte
  //테스트 URL:
  //=> http://localhost:8080/lang/variable/exam3/test1?value=100
  @GetMapping("/test1")
  public String test1(byte value) {
    return "==> " + value;
  }

  // 2) "32767" ==> short
  //테스트 URL:
  //=> http://localhost:8080/lang/variable/exam3/test1?value=32767
  @GetMapping("/test2")
  public String test2(short value) {
    return "==> " + value;
  }

  // 3) "456789" ==> int
  //테스트 URL:
  //=> http://localhost:8080/lang/variable/exam3/test1?value=456789
  @GetMapping("/test3")
  public String test3(int value) {
    return "==> " + value;
  }

  // 4) "2244445555" ==> long
  //테스트 URL:
  //=> http://localhost:8080/lang/variable/exam3/test1?value=2244445555
  @GetMapping("/test4")
  public String test4(long value) {
    return "==> " + value;
  }

  // 5) "3.14" ==> float
  //테스트 URL:
  //=> http://localhost:8080/lang/variable/exam3/test1?value=3.14
  @GetMapping("/test5")
  public String test5(float value) {
    return "==> " + value;
  }

  // 6) "3456.7898" ==> double
  //테스트 URL:
  //=> http://localhost:8080/lang/variable/exam3/test1?value=3456.7898
  @GetMapping("/test6")
  public String test6(double value) {
    return "==> " + value;
  }

  // 7) "true" ==> boolean
  // - "true", "1", "TRUE" ==> true
  // - "false", "0", "FALSE" ==> false 
  //테스트 URL:
  //=> http://localhost:8080/lang/variable/exam3/test1?value=true
  @GetMapping("/test7")
  public String test7(boolean value) {
    return "==> " + value;
  }

  // 8) "가" ==> char
  //테스트 URL:
  //=> http://localhost:8080/lang/variable/exam3/test1?value=가
  @GetMapping("/test8")
  public String test8(char value) {
    return "==> " + value;
  }

  // 9) "2021-12-06" ==> Date
  //테스트 URL:
  //=> http://localhost:8080/lang/variable/exam3/test1?value=2021-12-06
  @GetMapping("/test9")
  public String test9(java.sql.Date value) {
    return "==> " + value;
  }

}







