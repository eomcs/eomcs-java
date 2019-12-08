// Object 클래스 - equals()에 대하여  
package ch15;

class My4 {
  String name;
  int age;
}

public class Test04 {
  public static void main(String[] args) {
    My4 obj1 = new My4();
    obj1.name = "홍길동";
    obj1.age = 20;
    
    My4 obj2 = new My4();
    obj2.name = "홍길동";
    obj2.age = 20;
    
    System.out.println(obj1 == obj2);
    
    // Object에서 상속 받은 equals()는 인스턴스가 같은지 비교한다.
    // 만약 그 내용물이 같은지 비교하고 싶다면 equals()를 재정의 하라!
    System.out.println(obj1.equals(obj2));

  }

}







