// 인스턴스 배열?
package ch07;

public class Test08 {

  static class Score {
    String name; 
    int kor;
    int eng;
    int math;
    int sum;
    float aver;
  }
  
  public static void main(String[] args) throws Exception {
    
    // 낱개의 int 변수 선언
    int v1, v2, v3;
    
    // int 배열 선언
    int[] values = new int[3];
    // values : 배열의 주소를 담는 변수(레퍼런스)
    // new int[3] : int 변수 3개 생성한다. 각 항목에 저장하는 값은 int 값!
    
    // 낱개의 레퍼런스 선언;
    Score s1, s2, s3;
    
    // Score 변수 3개를 만드는 배열 선언
    // => Score 변수란 Score 인스턴스 주소를 담는 레퍼런스를 말한다.
    // => Score 레퍼런스를 3개 만든다.
    Score[] scores = new Score[3];
    // scores : 배열의 주소를 저장하는 레퍼런스이다.
    // new Score[3] : Score 레퍼런스를 3개 생성한다. 주의! 인스턴스를 생성하는 것이 아니다.
    // 강사님! 인스턴스 배열을 만들 수는 없나요? 없다!
    
    // 레퍼런스 배열을 생성하면 모든 항목이 null로 초기화 된다.
    // 아직 레퍼런스 배열의 각 항목에 인스턴스 주소가 없는 상태이다.
    // 이런 상태에서 레퍼런스 배열에 있는 항목을 사용하려 하면 NullPointerException 오류가 발생한다! 
    //System.out.println(scores[0].name); // 실행 오류! (runtime exception 발생)'
    
    // 배열 레퍼런스에 인스턴스 주소를 넣은 다음에 사용하라.
    scores[0] = new Score();
    scores[1] = new Score();
    scores[2] = new Score();
    
    // 레퍼런스 배열의 각 항목이 유효한 인스턴스의 주소를 갖고 있을 때 사용할 수 있다.
    System.out.println(scores[0].name);
    System.out.println(scores[0].kor);
    
    // 잊지말자!
    // - 인스턴스 배열을 만들 수 없다.
    // - 레퍼런스 배열만 가능하다.
    // - 단 자바 기본 타입(byte, short, int, long, float, double, boolean, char)은
    //   해당 타입의 메모리를 배열로 만들 수 있다.
    // - 그 외 모든 타입은 오직 레퍼런스 배열만 생성한다.
    
  }
}
















