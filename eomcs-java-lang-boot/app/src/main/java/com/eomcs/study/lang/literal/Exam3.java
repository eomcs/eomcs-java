// 리터럴: 정수 리터럴이 표현할 수 있는 값의 범위
package com.eomcs.study.lang.literal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lang/literal/exam3")
public class Exam3 {

  @GetMapping("/test1")
  public String test1() {
    //return "정수: " + 21_4748_3648; // 컴파일 오류! 4바이트 메모리에 저장할 수 없다.
    return "정수: " + 21_4748_3647;
    // 문자열과 숫자를 더하면?
    // => 문자열 + (숫자 --> 문자열) = 한 개의 문자열로 합친다.
  }

  @GetMapping("/test2")
  public String test2() {
    //return "정수: " + -21_4748_3649; // 컴파일 오류! 4바이트 메모리에 저장할 수 없다.
    return "정수: " + -21_4748_3648;
  }

  @GetMapping("/test3")
  public String test3() {
    return "정수: " + 21_4748_3648L; // OK! 리터럴을 저장할 때 8바이트 메모리를 사용하라고 표시한다. 
  }

  @GetMapping("/test4")
  public String test4() {
    return "정수: " + -21_4748_3649l; // OK! 리터럴을 저장할 때 8바이트 메모리를 사용하라고 표시한다. 
  }

  @GetMapping("/test5")
  public String test5() {
    return "정수: " + 922_0000_0000_0000_0000L; // OK! 리터럴을 저장할 때 8바이트 메모리를 사용하라고 표시한다. 
  }

  @GetMapping("/test6")
  public String test6() {
    return "정수: " + -922_0000_0000_0000_0000l; // OK! 리터럴을 저장할 때 8바이트 메모리를 사용하라고 표시한다. 
  }
}







