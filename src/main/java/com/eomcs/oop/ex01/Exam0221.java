package com.eomcs.oop.ex01;

//# 레퍼런스 배열 - 사용 후
//
public class Exam0221 {
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
    // 강사님, 인스턴스 배열을 만들 수는 없나요?
    // => 없다!

    // 레퍼런스 배열을 생성하면 모든 항목이 null로 초기화 된다.
    // 아직 레퍼런스 배열의 각 항목에 인스턴스 주소가 없는 상태이다.
    System.out.println(arr[0]);
    System.out.println(arr[1]);
    System.out.println(arr[2]);

    // 레퍼런스 배열의 각 항목에 인스턴스를 저장한 후 사용해야 한다.
    arr[0] = new Score();
    arr[1] = new Score();
    arr[2] = new Score();

    // 배열의 각 항목에 저장된 인스턴스는 '배열명[인덱스]'를 사용하여 지정할 수 있다.
    arr[0].name = "홍길동";
    arr[0].kor = 100;
    arr[0].eng = 100;
    arr[0].math = 100;
    arr[0].sum = arr[0].kor + arr[0].eng + arr[0].math;
    arr[0].aver = arr[0].sum / 3f;

    arr[1].name = "임꺽정";
    arr[1].kor = 90;
    arr[1].eng = 90;
    arr[1].math = 90;
    arr[1].sum = arr[1].kor + arr[1].eng + arr[1].math;
    arr[1].aver = arr[1].sum / 3f;

    arr[2].name = "유관순";
    arr[2].kor = 80;
    arr[2].eng = 80;
    arr[2].math = 80;
    arr[2].sum = arr[2].kor + arr[2].eng + arr[2].math;
    arr[2].aver = arr[2].sum / 3f;

    System.out.printf("%s: %d, %d, %d, %d, %.1f\n",
        arr[0].name, arr[0].kor, arr[0].eng, arr[0].math, arr[0].sum, arr[0].aver);
    System.out.printf("%s: %d, %d, %d, %d, %.1f\n",
        arr[1].name, arr[1].kor, arr[1].eng, arr[1].math, arr[1].sum, arr[1].aver);
    System.out.printf("%s: %d, %d, %d, %d, %.1f\n",
        arr[2].name, arr[2].kor, arr[2].eng, arr[2].math, arr[2].sum, arr[2].aver);
  }
}

// 결론!
// - 여러 개의 인스턴스의 주소를 관리할 때는
//   레퍼런스 배열을 사용하는 게 편하다.








