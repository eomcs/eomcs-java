// 클래스, 레퍼런스, 인스턴스 
package ch07;

public class Test04 {
  // 클래스
  // => 사용자 정의 데이터 타입
  // => 개발자가 애플리케이션에서 특정 정보를 저장할 목적으로 정의한 메모리 설계도
  static class Score {
    String name; 
    int kor;
    int eng;
    int math;
    int sum;
    float aver;
  }
  
  public static void main(String[] args) throws Exception {
    
    // 레퍼런스(reference)
    // => 클래스 설계도에 따라 준비된 "메모리의 주소를 담는 변수"
    Score s1;  
    
    // 인스턴스(instance)
    // => 클래스 설계도에 따라 준비된 메모리(변수들)
    s1 = new Score(); 

    // 인스턴스 필드(instance field)
    // => 클래스 설계도에 따라 준비된 메모리의 각 항목
    s1.name = "홍길동";
    s1.kor = 100;
    s1.eng = 100;
    s1.math = 100;
    
    printScore(s1); 
    
  }
  
  static void printScore(Score s) {
    s.sum = s.kor + s.eng + s.math;
    s.aver = s.sum / 3f;
    System.out.printf("%s: %d, %d, %d, %d, %f\n", 
        s.name, s.kor, s.eng, s.math, s.sum, s.aver);
  }
  
}

















