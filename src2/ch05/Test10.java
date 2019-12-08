// 흐름제어문 - 중첩 while 과 break;
package ch05;

public class Test10 {
  
  public static void main(String[] args) {
    // 중첩 반복문의 활용
    int a = 1;
    while (a <= 10) { 
      int b = 1;
      while (b <= a) {
        System.out.print(b++ + " ");
      }
      System.out.println();
      a++;
    }
    System.out.println("-------------------------");
    
    // 중첩 반복문에서 가장 바깥의 반복문을 나가기
    a = 1;
    while (a <= 10) { 
      int b = 1;
      while (b <= a) {
        System.out.print(b++ + " ");
        if (b > 5)
          break; // 현재 소속된 반복문을 나간다.
      }
      System.out.println();
      a++;
    }
    System.out.println("-------------------------");
    
    // 중첩 반복문에서 가장 바깥의 반복문을 나가기 II
    a = 1;
    // 반복문에 라벨을 붙인다.
    okok: // okok 라벨은 while() 문에 대한 별명이다.
    while (a <= 10) { 
      int b = 1;
      while (b <= a) {
        System.out.print(b++ + " ");
        if (b > 5)
          break okok; // break에 라벨명을 지정하면 그 라벨이 붙은 문장을 나간다.
      }
      System.out.println();
      a++;
    }
    
    System.out.println("프로그램 종료!");
  }
}

/*
# 라벨 
- 문장에 별명을 붙이는 문법이다.
  라벨명: 문장1;

- 만약 여러 문장에 라벨을 붙이고 싶다면 블록으로 묶어라
  라벨명: {
    문장1; 
    문장2; 
    문장3;
  }
 */













