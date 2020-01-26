package com.eomcs.oop.ex01;

// # 클래스 사용 : 5) 메서드 활용 II
//
public class Exam0114 {

  static class Score {
    String name;
    int kor;
    int eng;
    int math;
    int sum;
    float aver;
  }

  public static void main(String[] args) {

    // 클래스를 이용하면 성적 정보와 같은 여러 개의 값을 한 번에 리턴 받을 수 있다.
    Score s = createScore("홍길동", 100, 100, 100);

    printScore(s);
  }

  static void printScore(Score s) {
    s.sum = s.kor + s.eng + s.math;
    s.aver = s.sum / 3;
    System.out.printf("%s: %d, %d, %d, %d, %.1f\n",
        s.name, s.kor, s.eng, s.math, s.sum, s.aver);
  }

  // 클래스를 이용하면 성적 정보를 하나로 묶어 리턴할 수 있다.
  // - Score 인스턴스를 생성하여 리턴한다.
  // - 더 정확하게 표현하면, Score 인스턴스를 생성한 후 그 주소를 리턴한다.
  static Score createScore(String name, int kor, int eng, int math) {
    Score s = new Score();

    s.name = "홍길동";
    s.kor = 100;
    s.eng = 90;
    s.math = 80;

    return s; // s에 저장된 인스턴스의 주소를 리턴한다.
    // 강사님! 로컬 변수는 메서드 호출이 끝난 다음에 삭제된다고 했는데
    // 위에서 생성한 s 변수도 삭제되고, name, kor, eng, math도 삭제되는게 아닌가요?
    // => 예 삭제됩니다.
    //
    // 하나 더요? Score 인스턴스도 삭제되는게 아닌가요?
    // => 삭제되지 않습니다. 인스턴스는 힙(heap) 메모리에 생성됩니다.
    //    어떤 메서드에서 인스턴스를 생성하든지 간에 힙에 생성된 것은 메서드 호출이 끝나더라도
    //    삭제되지 않습니다.
    //    오직 스택(stack) 메모리에 생성된 로컬 변수만이 삭제됩니다.
  }
}








