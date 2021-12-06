// 변수 : 스프링 부트의 자동 형변환
package com.eomcs.study.lang.variable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("lang.variable.Exam3") // 클래스 이름이 같은 경우 충돌 방지
@RequestMapping("/lang/variable/exam3")
public class Exam3 {

  //스프링 부트는 클라이언트가 보낸 값을 파라미터 타입에 맞춰 자동 형변환을 수행한다.
  //테스트 URL:
  //=> http://localhost:8080/lang/variable/exam3/test1?b=100&s=200&i=654321&l=9220000000000000000&f=3.14&d=345.678&bool=true&c=가
  @GetMapping("/test1")
  public String test1(byte b, short s, int i, long l, float f, double d, boolean bool, char c) {
    return "클라이언트에서 받은 값 = " + b + "," + s + "," + i + "," + l + "," + f + "," + d + "," + bool + "," + c;
  }

}







