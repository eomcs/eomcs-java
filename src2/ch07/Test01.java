// 클래스 사용 전
package ch07;

public class Test01 {
  public static void main(String[] args) {
    // 학생의 성적 정보를 다루고 싶다.
    // 학생의 성적 정보는 이름, 국어, 영어, 수학, 합계, 평균 값들로 되어 있다.
    // 자바는 우리가 원하는 형식의 값(성적 데이터)을 저장할 수 있는 메모리 유형을 제공하지 않는다.
    // 단 데이터의 최소 형식인 byte, short, int, long, float, double, boolean, char, String 
    // 등의 값을 담을 수 있는 메모리만 제공한다.
    // 해결책? 
    // 낱개의 데이터를 저장할 변수를 여러 개 선언하는 수 밖에 없다!
    // 그래서 우리는 성적 데이터를 저장할 변수를 다음과 같이 낱개로 선언하였다.
    String name = "홍길동";
    int kor = 100;
    int eng = 100;
    int math = 100;
    int sum = kor + eng + math;
    float aver = sum / 3f;
    
    System.out.printf("%s: %d, %d, %d, %d, %f\n", name, kor, eng, math, sum, aver);
    // 음.. 할만하다!
  }
  
}
















