// 흐름제어문 - if ~ else if ~ else if ~ else 
package ch05;

public class Test03 {
  public static void main(String[] args) {
    int age = 70;

    // 기본 형식
    if (age < 8) 
      System.out.println("유아입니다.");
    else {
      if (age >= 8 && age < 14) 
        System.out.println("어린이입니다.");
      else {
        if (age >= 14 && age < 65)
          System.out.println("성인입니다.");
        else 
          System.out.println("노인입니다.");
      }
    }
    
    // 변형
    // => if ~ else 는 한 문장이다.
    // => 따라서 if ~ else 만 있을 때는 중괄호로 묶지 않아도 된다.
    if (age < 8) 
      System.out.println("유아입니다.");
    else 
      if (age >= 8 && age < 14) 
        System.out.println("어린이입니다.");
      else 
        if (age >= 14 && age < 65)
          System.out.println("성인입니다.");
        else 
          System.out.println("노인입니다.");
      
    // 그래서 if ~ else 를 작성할 때는 다음과 같이 표현한다.
    if (age < 8) 
      System.out.println("유아입니다.");
    else if (age >= 8 && age < 14) 
      System.out.println("어린이입니다.");
    else if (age >= 14 && age < 65)
      System.out.println("성인입니다.");
    else 
      System.out.println("노인입니다.");
    
    // 결론! 
    // => if ~ else if ~ else if ~ else 문법은 없다.
    // => 오직 if ~ else 문법만 있을 뿐이다.
    
    // 다음 실행 결과는?
    age = 17;
    if (age > 19) 
      if (age >= 65)
        System.out.println("노인입니다.");
    else 
      System.out.println("미성년입니다.");
    // 들여쓰기에 속지 말라!
    // else는 가장 가까운 if에 종속된다.
    // 따라서 위의 else는 바로 위의 if에 종속된다.
  }
}















