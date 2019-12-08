package ch12.b;

// 계산 결과에 영향을 미치는 필드에도 getter/setter를 적용한다.
//
public class Score2 {
  
  // name은 직접 접근해도 되는데, 다른 필드와 사용법을 동일하게 가져가기 위해 
  // 직접 접근을 막고 getter/setter를 통해 값을 다루도록 한다.
  // 이렇게 하면 나중에 name에 대해 값의 유효성을 검사하는 코드를 즉시 삽입할 수 있으니까
  // 유지보수에도 도움이 된다.
  // 그래서 실무에서는 그냥 모든 필드를 private이나 protected로 접근을 제한한 다음에
  // setter/getter를 두는 방식으로 프로그래밍을 한다.
  // setter/getter가 필요없는 필드라도 그냥 관성적으로 그렇게 한다.
  // 고민하지 말고 여러분도 그냥 이렇게 하라!
  // setter에서 유효성을 검사하지 않더라도 그냥 만들라!
  //
  private String name;
  
  // 점수를 변경할 때 계산을 다시 해야 하고, 유효하지 않은 값을 넣지 못하도록 막아야 한다.
  // 따라서 직접 접근하는 것을 막는다.
  private int kor;
  private int eng;
  private int math;
  
  // 계산 결과를 조작하지 못하도록 접근을 제한하자!
  private int sum;
  private float aver;
  
  // 대신 결과 값을 꺼낼 수 있는 메서드(getter)를 제공해야 한다.
  public String getName() {
    return this.name;
  }
  public void setName(String name) {
    // 이렇게 유효성을 검사하지 않더라도 setter를 그냥 만들라!
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
  
  // 점수를 변경할 때 마다 호출되기 때문에 임의로 호출할 필요가 없다.
  // 따라서 비공개로 만든다.
  // 초보 개발자의 많은 착각!
  // => 필드는 무조건 private, 메서드는 무조건 public 으로 해야 한다고 생각한다.
  // => 착각이다. 필드든 메서드든 공개할 것은 공개하고 공개하지 말아야 하는 것은 공개하지 말라.
  // => 기본이 비공개이고, 공개할 것만 공개하라! 
  //    이렇게 하는 것이 클래스가 잘못 사용되는 상황을 방지할 수 있다.
  private void compute() {
    this.sum = this.kor + this.eng + this.math;
    this.aver = this.sum / 3f;
  }
}







