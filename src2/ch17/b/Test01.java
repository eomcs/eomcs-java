// 같은 타입으로 묶지 않았을 때
package ch17.b;

import java.util.Scanner;

public class Test01 {

  public static void main(String[] args) {
    int[] values = {23, 7, 12, 15, 9, 2, 22, 8, 11, 25, 13, 5};
    int[] values2 = {23, 7, 12, 15, 9, 2, 22, 8, 11, 25, 13, 5};
    
    BubbleSort s1 = new BubbleSort();
    QuickSort s2 = new QuickSort();

    // 두 개의 정렬 객체가 서로 같은 타입이 아니기 때문에 
    // 정렬을 수행하고 출력할 메서드를 따로 따로 만들어야 했다.
    // 클래스의 사용법도 달라서 불편했다.
    // BubbleSort.run(), QuickSort.start()
    display(s1, values);
    display(s2, values2);
    
  }

  // 정렬을 수행하는 객체와 값을 주면 
  // 그 값을 정렬한 후 출력하는 메서드이다.
  static void display(BubbleSort sorter, int[] values) {
    
    // 출력하기 전에 정렬을 먼저 수행한다.
    sorter.run(values);
    
    // 정렬된 값을 출력한다.
    for (int  value : values) {
      System.out.print(value + ",");
    }
    System.out.println();
  }
  
  static void display(QuickSort sorter, int[] values) {
    
    // 출력하기 전에 정렬을 먼저 수행한다.
    sorter.start(values, 0, values.length - 1);
    
    // 정렬된 값을 출력한다.
    for (int  value : values) {
      System.out.print(value + ",");
    }
    System.out.println();
  }
}






