// 추상 클래스: 리턴 타입으로 사용하기
package ch13.j;

public class Test03 {

  public static void main(String[] args) {
    DumpTruck car = m1(); 
    Car car2 = m2();
  }
  
  static DumpTruck m1() {
    // 이 메서드의 리턴 값은 
    // DumpTruck 인스턴스 (주소) 이거나 
    // DumpTruck의 하위 클래스의 인스턴스 (주소) 이다.
    return new DumpTruck();
  }
  
  static Car m2() {
    // 이 메서드의 리턴 값은 Car의 인스턴스가 아니다.
    // 왜? Car는 추상클래스이기 때문에 인스턴스를 생성할 수 없다.
    // 그럼? Car의 하위 클래스의 인스턴스를 리턴한다는 뜻이다.
    return new DumpTruck();
  }

}









