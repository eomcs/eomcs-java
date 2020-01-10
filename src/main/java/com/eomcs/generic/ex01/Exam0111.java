// 제네릭(Generic) - 사용 전: 다형적 변수 활용
package com.eomcs.generic.ex01;

public class Exam0111 {
  
  // 다양한 객체를 저장하는 Box를 만들기 위해 
  // 인스턴스의 변수 타입을 Object로 선언한다.
  // => 그러면 다음과 같이 한 개의 Box 클래스만 정의하면 된다.
  //
  static class ObjectBox {
    Object value;
    
    public void set(Object value) {
      this.value = value;
    }
    
    public Object get() {
      return value;
    }
  }
  
  public static void main(String[] args) {
    
    ObjectBox box1 = new ObjectBox();
    box1.set(new Member("홍길동", 20)); // 값 저장
    Member m = (Member) box1.get(); // 값을 꺼낼 때 형변환해야 한다.
    System.out.println(m);
    
    ObjectBox box2 = new ObjectBox();
    box2.set(new String("Hello"));
    String str = (String) box2.get(); // 값을 꺼낼 때 원래의 타입으로 형변환 한다.
    System.out.println(str);
    
    ObjectBox box3 = new ObjectBox();
    box3.set(100); // auto-boxing ==> Integer.valueOf(100)
    int i = (int) box3.get(); // 값을 꺼낼 때 primitive date type을 지정하면 
                              // 자동으로 auto-unboxing으로 바꾼다.
    System.out.println(i);
    
    // 객체의 타입 별로 Box 클래스를 구분해서 쓰지 않으니 코딩이 편하다. 
    // 단 값을 꺼낼 때 원래의 타입으로 바꾸기 위해 형변환(type casting) 해야 하는 불편함이 있다.
    //
    
  }
}








