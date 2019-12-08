package ch12.c;

public class My {
  // private: 내부 멤버
  private int v1;
  
  // (default): 내부 멤버 + 같은 패키지 소속 
  int v2; // default
  
  // protected : 내부 멤버 + 같은 패키지 소속 + (상속 받은 필드인 경우) 서브 클래스
  protected int v3;
  
  // public : 완전 공개
  public int v4;
  
  public void m1() {
    this.v1 = 100; // 내부 멤버만이 private 멤버에 접근할 수 있다.
  }
}
