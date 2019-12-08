// 흐름제어문 - switch 과 문자열
package ch05;

public class Test05 {
  public static void main(String[] args) {
    String str = "yes";
    
    switch (str) {
      case "ok":
        System.out.println("okokokok");
        break;
      case "no":
        System.out.println("nononono");
        break;
      default:
        System.out.println("????");
    }
  }
}

/*
# switch 문법

  switch (4바이트 정수 값 또는 문자열) {
  case 4바이트정수값 또는 문자열:
    문장1;
    문장2;
    break;
  case xxxx:
    문장1;
    ...
    break;
  default: 
    위의 조건에 해당되는 것이 없으면 이 문장을 실행한다.
  }
 */













