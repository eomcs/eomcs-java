// generalization을 통해 수퍼 클래스를 정의할 때 문제점
package ch17.c;

public class Test02 {

  public static void main(String[] args) {
    int[] values = {23, 7, 12, 15, 9, 2, 22, 8, 11, 25, 13, 5};
    
    // => Sorter는 BubbleSort와 QuickSort 클래스처럼 
    //    단지 정렬을 수행하는 클래스를 만들 때 일관성 있게 사용하려고
    //    수퍼 클래스로서 정의한 것이다.
    // => Sorter는 직접 사용하려고 만든 것이 아니다.
    //
    // 다음과 같이 Sorter를 직접 사용하는 것은 아무런 의미가 없다.
    // 왜? 정렬을 수행하는 메서드인 sort()가 아무 일도 안한다.
    display(new Sorter(), values);

    // 이런 경우, 차라리 Sorter 클래스를 추상 클래스로 만드는 것이 바람직하다.
    // 추상 클래스인 경우 직접 인스턴스를 만들어 사용할 수 없기 때문이다.
    
  }

  static void display(Sorter sorter, int[] values) {
    
    // 정렬 객체의 클래스가 뭔지 상관없다.
    // 그 클래스를 사용할 때는 공통 분모가 되는 
    // 수퍼 클래스의 메서드를 호출한다.
    sorter.sort(values);
    
    // 정렬된 값을 출력한다.
    for (int  value : values) {
      System.out.print(value + ",");
    }
    System.out.println();
  }
}






