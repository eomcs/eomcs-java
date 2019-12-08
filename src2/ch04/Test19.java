// 비트 연산자 응용
// 
package ch04;

public class Test19 {
  public static void main(String[] args) {
    // 한 개의 정수 값에 여러 개의 정보를 저장하기
    // => 비트의 1 또는 0을 이용하여 정보를 표현할 수 있다.
    // 
    // 개발자가 프로그래밍 가능한 언어를 int 값으로 표현해 보자!
    // Java         : 0000 0000 0000 0001 = 0x01
    // C            : 0000 0000 0000 0010 = 0x02
    // C++          : 0000 0000 0000 0100 = 0x04
    // Python       : 0000 0000 0000 1000 = 0x08
    // PHP          : 0000 0000 0001 0000 = 0x10
    // Kotlin       : 0000 0000 0010 0000 = 0x20
    // Groovy       : 0000 0000 0100 0000 = 0x40
    // JavaScript   : 0000 0000 1000 0000 = 0x80
    // TypeScript   : 0000 0001 0000 0000 = 0x0100
    // Go           : 0000 0010 0000 0000 = 0x0200
    // SQL          : 0000 0100 0000 0000 = 0x0400
    // R            : 0000 1000 0000 0000 = 0x0800
   
    // Java와 C, C++, JavaScript를 할 줄 아는 개발자의 정보를 설정하라!
    int d1 = 0x01 | 0x02 | 0x04 | 0x80; // 0000 0000 1000 0111
    int d2 = 0x0087;
    
    System.out.println(d1);
    System.out.println(d2);
    
    
    // 정수 값에서 특정 비트의 값만 검사하는 방법
    //   0000 0000 0001 0111 (여러 프로그래밍 언어에 대해 설정된 값) 
    // TypeScript 언어를 할 줄 아는지 검사하기
    //     0000 0000 0001 0111
    //   & 0000 0001 0000 0000 (TypeScript 값을 조사하려는 값과 AND 한다.)
    //   ----------------------  (조사하려는 값에 0을 AND 하면 무조건 0으로 만든다.)
    //     0000 0000 0000 0000   (조사하려는 값에 1을 AND 하면 원래 값 그래도 리턴한다.) 
    // AND 결과 값을 검사 값과 같은지 비교하면 된다.
    //     0000 0000 0000 0000 (결과값)
    //     0000 0001 0000 0000 (TypeScript 여부를 조사하는 값)
    // => 결과 값과 조사한 값이 같지 않으면 해당 비트가 0이라는 의미다.
    
    System.out.printf("Java      : %b\n", (d2 & 0x0001) == 0x0001);
    System.out.printf("C         : %b\n", (d2 & 0x0002) == 0x0002);
    System.out.printf("C++       : %b\n", (d2 & 0x0004) == 0x0004);
    System.out.printf("Python    : %b\n", (d2 & 0x0008) == 0x0008);
    System.out.printf("PHP       : %b\n", (d2 & 0x0010) == 0x0010);
    System.out.printf("Kotlin    : %b\n", (d2 & 0x0020) == 0x0020);
    System.out.printf("Groovy    : %b\n", (d2 & 0x0040) == 0x0040);
    System.out.printf("JavaScript: %b\n", (d2 & 0x0080) == 0x0080);
    System.out.printf("TypeScript: %b\n", (d2 & 0x0100) == 0x0100);
    System.out.printf("Go        : %b\n", (d2 & 0x0200) == 0x0200);
    System.out.printf("SQL       : %b\n", (d2 & 0x0400) == 0x0400);
    System.out.printf("R         : %b\n", (d2 & 0x0800) == 0x0800);
    
    System.out.println("------------------------------");
    // 값이 같지 않으면 어차피 결과는 0이다.
    // 그래서 다음과 같이 검사해도 된다.
    System.out.printf("Java      : %b\n", (d2 & 0x0001) != 0);
    System.out.printf("C         : %b\n", (d2 & 0x0002) != 0);
    System.out.printf("C++       : %b\n", (d2 & 0x0004) != 0);
    System.out.printf("Python    : %b\n", (d2 & 0x0008) != 0);
    System.out.printf("PHP       : %b\n", (d2 & 0x0010) != 0);
    System.out.printf("Kotlin    : %b\n", (d2 & 0x0020) != 0);
    System.out.printf("Groovy    : %b\n", (d2 & 0x0040) != 0);
    System.out.printf("JavaScript: %b\n", (d2 & 0x0080) != 0);
    System.out.printf("TypeScript: %b\n", (d2 & 0x0100) != 0);
    System.out.printf("Go        : %b\n", (d2 & 0x0200) != 0);
    System.out.printf("SQL       : %b\n", (d2 & 0x0400) != 0);
    System.out.printf("R         : %b\n", (d2 & 0x0800) != 0);
    
  }
}

















