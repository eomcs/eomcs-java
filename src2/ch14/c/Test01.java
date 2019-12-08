// 오버로딩 테스트 
package ch14.c;

public class Test01 {

  public static void main(String[] args) {
    Calculator obj = new Calculator();
    
    // 파라미터의 타입과 개수를 보고 호출할 메서드를 결정한다.
    obj.plus(100); // plus(int)
    obj.plus(20, 30); // plus(int, int)
    obj.plus(new int[] {30, 30, 20}); // plus(int[])
    
    System.out.println(obj.result);

    Calculator2 obj2 = new Calculator2();
    obj2.plus(100);
    obj2.minus(50); // minus(int)
    obj2.minus(30, 10); // minus(int, int)
    
    System.out.println(obj2.result);
    
  }

}






