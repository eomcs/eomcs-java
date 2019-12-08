// Wrapper 클래스와 오토박싱(auto-boxing)/오토언박싱(auto-unboxing)
package ch11;

public class Test10 {
  public static void main(String[] args) {
    // primitive type의 값을 인스턴스에 담고("박싱;boxing"이라 부른다)
    // 인스턴스의 담긴 primitive 값을 다시 꺼내는 작업("언박싱;unboxing"이라 부른다)이 매우 불편한다.
    
    Integer obj1 = Integer.valueOf(100); // boxing
    
    int i1 = obj1.intValue(); // unboxing
    
    // 그래서 자바는 boxing과 unboxing을 코드 문맥에 따라 자동으로 수행한다.
    int i2 = obj1; // auto-unboxing. 즉 내부적으로 obj1.intValue()를 호출한다.
    Integer obj2 = 200; // auto-boxing. 즉 내부적으로 Integer.valueOf(200)을 호출한다.
    
    printInt(obj2); // 컴파일러가 printInt(obj2.intValue())로 바꾼다. "오토 언박싱" 한다.
    
    printObject(100); // 컴파일러가 printObject(Integer.valueOf(100)) 으로 바꾼다.
                      // 즉 "오토 박싱"을 수행한다.
  }
  
  static void printInt(int value) {
    System.out.println(value);
  }
 
  static void printObject(Integer obj) {
    System.out.println(obj.toString());
  }
  
}
















