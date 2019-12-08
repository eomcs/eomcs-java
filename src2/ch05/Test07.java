// 흐름제어문 - switch 과 break;
package ch05;

public class Test07 {
  
  public static void main(String[] args) {
    String type = "개발팀장";
    
    // break 문을 쓰지 않으면 계속 이어서 실행된다. 
    // => 일부러 쓰지 않을 때가 있다.
    switch (type) {
      case "개발팀장":
        System.out.println("개발 경력 10년 이상 또는 석사 이상");
      case "개발자" :
        System.out.println("정보처리 자격증");
      case "사무직":
        System.out.println("전문학사 이상");
      default:
        System.out.println("이력서");
    }
    
    int score = 60;
    // break 문을 쓰지 않으면 계속 이어서 실행된다. 
    // => 일부러 쓰지 않을 때가 있다.
    switch (score) {
      case 100:
      case 90:
        System.out.println("A");
        break;
      case 80:
      case 70:
        System.out.println("B");
        break;
      case 60:
      case 50:
      case 40:
        System.out.println("C");
        break;
      default:
        System.out.println("F");
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













