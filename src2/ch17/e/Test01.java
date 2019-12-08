// 추상 메서드의 효용성 - 서브 클래스에서 구현하도록 강제하는 효과가 있다.
package ch17.e;

public class Test01 {

  public static void main(String[] args) {
    int[] values = {23, 7, 12, 15, 9, 2, 22, 8, 11, 25, 13, 5};
    int[] values2 = {23, 7, 12, 15, 9, 2, 22, 8, 11, 25, 13, 5};
    int[] values3 = {23, 7, 12, 15, 9, 2, 22, 8, 11, 25, 13, 5};
    
    // Sorter 클래스의 sort() 메서드를 추상 메서드이기 때문에
    // Sorter 클래스를 상속 받은 서브 클래스가 무엇이든 간에
    // sort() 메서드가 반드시 구현되어 있을 것이다.
    // 따라서 display() 메서드에서는 파라미터로 넘어오는 Sorter가 
    // BubbleSort 인지, QuickSort 인지, MergeSort 인지 따질 필요 없이,
    // 그냥 서브 클래스가 구현한 sort()를 호출하면 된다.
    //
    display(new BubbleSort(), values);
    display(new QuickSort(), values2);
    display(new MergeSort(), values3);
    
  }

  static void display(Sorter sorter, int[] values) {
    
    // 어? 강사님! Sorter 클래스의 sort() 메서드는 추상 메서드인데 
    // 어떻게 호출할 수 있나요? 다음 코드는 오류가 아닌가요?
    // => JVM이 sort()를 호출할 때 레퍼런스에 있는 객체가 실제 어떤 클래스의 객체인지 판단한다.
    //    그런 후에 해당 클래스의 메서드를 호출하기 때문에 질문한 것과 같은 문제는 발생하지 않는다.
    //
    sorter.sort(values);
    
    // 정렬된 값을 출력한다.
    for (int  value : values) {
      System.out.print(value + ",");
    }
    System.out.println();
  }
}






