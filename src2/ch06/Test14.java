// main() 메서드의 파라미터 응용 I
package ch06;

public class Test14 {
  public static void main(String[] args) {
    // 합계를 출력하는 프로그램을 작성하라.
    // $ java -cp ./bin/main ch06.Test14 200 43 56
    //
    int sum = 0;
    for (String arg : args)
      sum += Integer.parseInt(arg);
    System.out.printf("합계: %d\n", sum);
  }
  
}

/*
# 프로그램 아규먼트(arguments)
- 프로그램을 실행할 때 넘겨주는 값.
- 어떻게 아규먼트를 넘기는가?

  $ java 클래스명 값1 값2 값3
  
- 아규먼트는 공백으로 구분한다.
- JVM은 아규먼트의 개수만큼 문자열 배열을 만들어 저장한다.
- 아규먼트가 없으면 빈 배열을 만든다.
- 그런후 main()을 호출할 때 그 배열의 주소를 넘겨준다.
 */













