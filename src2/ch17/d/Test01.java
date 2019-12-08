// generalization 기법으로 생성한 수퍼 클래스를 추상 클래스로 선언하기
package ch17.d;

public class Test01 {

  public static void main(String[] args) {
    int[] values = {23, 7, 12, 15, 9, 2, 22, 8, 11, 25, 13, 5};
    
    // => Sorter 클래스를 추상 클래스로 선언하면 다음과 같이 직접 사용할 수 없다.
    //display(new Sorter(), values); // 컴파일 오류!

    // Sorter 클래스처럼 서브 클래스에게 공통 필드나 기능을 상속해주는 용도로 만든 클래스인 경우
    // 직접 사용하지 못하게 abstract 클래스로 선언하면 
    // 다른 개발자가 잘못 사용하는 것을 막을 수 있다.
    
  }

  static void display(Sorter sorter, int[] values) {
    sorter.sort(values);
    for (int  value : values) {
      System.out.print(value + ",");
    }
    System.out.println();
  }
}






