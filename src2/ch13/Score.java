package ch13;

public class Score {
  
  private String name;
  protected int kor;
  protected int eng;
  protected int math; 
  
  // 서브 클래스에서 사용할 필드는 protected로 접근을 풀어 준다.
  // => 접근 범위를 넓히는 것은 기존 코드에 영향을 미치지 않는다.
  // => 접근 범위를 좁히게 되면 기존 코드에서 직접 접근하던 코드가 동작이 안될 수 있다.
  //    접근 범위를 좁히게 되면 기존 코드에 영향을 준다.
  protected int sum;
  protected float aver;
  
  public String getName() {
    return this.name;
  }
  public void setName(String name) {
    this.name = name;
  }
  
  public int getKor() {
    return this.kor;
  }
  public void setKor(int kor) {
    if (kor >= 0 && kor <= 100) { // 유효한 점수인 경우에만 저장한다.
      this.kor = kor;
      this.compute(); // 유효한 값이라면 다시 합계와 평균을 계산한다.
    }
  }
  
  public int getEng() {
    return this.eng;
  }
  public void setEng(int eng) {
    if (eng >= 0 && eng <= 100) { // 유효한 점수인 경우에만 저장한다.
      this.eng = eng;
      this.compute(); // 유효한 값이라면 다시 합계와 평균을 계산한다.
    }
  }
  
  public int getMath() {
    return this.math;
  }
  public void setMath(int math) {
    if (math >= 0 && math <= 100) {// 유효한 점수인 경우에만 저장한다.
      this.math = math;
      this.compute(); // 유효한 값이라면 다시 합계와 평균을 계산한다.
    }
  }
  
  public int getSum() {
    return this.sum;
  }
  
  public float getAver() {
    return this.aver;
  }
  
  // 서브 클래스에서 사용할 수 있도록 접근 범위를 넓힌다.
  protected void compute() {
    System.out.println("Score.compute() 호출됨!");
    this.sum = this.kor + this.eng + this.math;
    this.aver = this.sum / 3f;
  }
}







