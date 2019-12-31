//# 캡슐화 문법 사용 후 - 필드의 값을 변경할 때 마다 특정 작업을 수행하게 만들기
package com.eomcs.oop.ex07.a;

class Score3 {
  String name;
  
  // 국, 영, 수 점수를 바꿀 때 마다 자동으로 합계, 평균을 계산해야 한다.
  // 방법?
  // => 직접 필드의 값을 바꾸게 하지 말고 메서드를 통해 바꾸도록 유도한다.
  // => 이렇게 필드의 값을 바꿀 때 마다 뭔가를 수행해야 하는 경우,
  //    해당 필드의 직접 접근을 막아라!
  private int kor;
  private int eng;
  private int math;
  
  // 대신 메서드를 통해 값을 설정하게 하라!
  // 보통 필드의 값을 설정하는 메서드는 set필드명()으로 이름을 짓는다.
  // => 이런 메서드를 "세터(setter)"라 부른다.
  // => 외부에서 호출할 수 있도록 공개 모드로 설정한다.
  //
  public void setKor(int kor) {
    this.kor = kor;
  }
 
  public void setEng(int eng) {
    this.eng = eng;
  }
  
  public void setMath(int math) {
    this.math = math;
  }
  
  
  private int sum;
  private float aver;
  
  public int getSum() {
    return this.sum;
  }
  
  public float getAver() {
    return this.aver;
  }
  
  void compute() {
    this.sum = this.kor + this.eng + this.math;
    this.aver = this.sum / 3f;
  }
}

public class Exam0310 {
  public static void main(String[] args) {
    Score2 s1 = new Score2();
    s1.name = "홍길동";
    s1.kor = 100;
    s1.eng = 90;
    s1.math = 80;
    s1.compute();
    
    // 계산을 한 후에 임의적으로 합계나 평균을 변경한다면?
    // => sum과 aver 필드는 private 접근만 허용한다.
    // => 즉 클래스의 멤버만 접근할 수 있고 클래스 외부에서는 접근할 수 없다.
    // => 그래서 다음과 같이 임의로 접근하여 값을 변경할 수 없다.
    // 
    //s1.sum = 300; // 컴파일 오류!
    //s1.aver = 100f; // 컴파일 오류!
    
    System.out.printf("%s, %d, %d, %d, %d, %.1f\n",
        s1.name, s1.kor, s1.eng, s1.math, s1.getSum(), s1.getAver());
  }
}











