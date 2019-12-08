// Wrapper 클래스 생성자에 대한 고찰
package ch11;
class A {
  int i;
  Integer x;
}
public class Test11 {
  public static void main(String[] args) {
    // 왜 wrapper 클래스의 생성자를 쓰지 말라고 하는가?
    Integer obj1 = new Integer(100);
    Integer obj2 = new Integer(100);
    
    if (obj1 == obj2) 
      System.out.println("obj1 == obj2");
    else 
      System.out.println("obj1 != obj2");
    
    // 정수 값을 제대로 비교하려면 equals()를 호출해야 한다.
    // 물론, wrapper 클래스들도 String 클래스처럼 Object로부터 상속 받은 
    // equals()를 값을 비교하도록 변경하였다.
    if (obj1.equals(obj2))
      System.out.println("obj1.equals(obj2)");
    else 
      System.out.println("!obj1.equals(obj2)");
    
    // 간단한 숫자를 비교하는데 이렇게 equals()를 호출해야 한다면 얼마나 번거롭고 불편한가!
    // 그러나 new 를 사용하여 객체를 만들면 인스턴스 주소가 다르기 때문에 어쩔 수 없다.
    // 해결방안?
    // => 같은 값을 갖는 인스턴스인 경우 중복 생성하지 않고 한 개만 생성한다면 
    //    이런 번거로움을 피할 수 있다.
    // => 그래서 생성자를 사용하여 Integer 객체를 만들지 말고,
    //    valueOf()를 호출하여 Integer 객체를 만들라고 유도하는 것이다.
    // => valuesOf() 메서드는 같은 값을 갖는 인스턴스가 이미 있다면 
    //    새로 만들지 않고 기존 객체를 리턴한다.
    
    Integer obj3 = Integer.valueOf(100); 
    Integer obj4 = Integer.valueOf(100);
    
    // 이제 두 객체의 값을 비교할 때 equals()를 사용하지 않고 
    // 그냥 주소를 비교해도 된다.
    // 왜? 주소가 같다는 것은 같은 인스턴스라는 의미다. 결국 값이 같은 것 아닌가.
    //
    if (obj3 == obj4) 
      System.out.println("obj3 == obj4");
    else 
      System.out.println("obj3 != obj4");
    
    // 오토 박싱인 경우 인스턴스의 주소는?
    Integer obj5 = 100;
    Integer obj6 = 100;
    System.out.println(obj5 == obj6);
    System.out.println(obj3 == obj5);
    
    // Wrapper 객체를 valueOf()나 리터럴로 생성할 때는 
    // String 객체와 마찬가지로 상수 풀(constant pool)에 생성된다.
    // 그리고 같은 값인 경우 중복 생성되지 않는다.
  }
}
















