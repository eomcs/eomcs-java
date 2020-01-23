package com.eomcs.oop.ex01;

//# 레퍼런스 배열 - 사용 후
//
public class Exam0220 {
  public static void main(String[] args) {
    // 여러 개의 인스턴스 주소 저장하기
    
    class Score {
      String name;
      int kor;
      int eng;
      int math;
      int sum;
      float aver;
    }

    // 배열 문법을 이용하면 한 번에 여러 개의 레퍼런스를 선언할 수 있다.
    // - 문법:
    //     클래스명[] 배열명 = new 클래스명[레퍼런스개수];
    // - 주의!
    //   레퍼런스 배열이다. 인스턴스 배열이 아니다!
    // 
    Score[] arr = new Score[3];

    // 배열 레퍼런스의 각 항목에 인스턴스를 저장한 후 사용해야 한다.
    arr[0] = new Score();
    arr[1] = new Score();
    arr[2] = new Score();

    // 배열에 저장된 인스턴스는 '배열명[인덱스]'를 사용하여 지정할 수 있다. 
    arr[0].name = "홍길동";
    arr[1].name = "임꺽정";
    arr[2].name = "유관순";

    System.out.printf("이름: %s\n", arr[0].name);
    System.out.printf("이름: %s\n", arr[1].name);
    System.out.printf("이름: %s\n", arr[2].name);
  }
}

// 결론!
// - 여러 개의 인스턴스의 주소를 관리할 때는 
//   레퍼런스 배열을 사용하는 게 편하다.








