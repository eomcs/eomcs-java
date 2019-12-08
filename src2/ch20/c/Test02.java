// java.util.Set 클래스 사용법 - add()와 hashCode(), equals()
package ch20.c;

import java.util.HashSet;
import java.util.Iterator;

public class Test02 {

  public static void main(String[] args) {
    class Student {
      String name;
      int age;
      
      public Student(String name, int age) {
        this.name = name;
        this.age = age;
      }

      @Override
      public String toString() {
        return "Student [name=" + name + ", age=" + age + "]";
      }
    }
    
    HashSet<Student> set = new HashSet<>();
    
    // Set에 값 추가하기
    set.add(new Student("aaa", 20));
    set.add(new Student("bbb", 30));
    set.add(new Student("ccc", 40));
    
    // Set은 집합의 특성을 따른다.
    // => 같은 값을 중복해서 넣을 수 없다.
    // => 같은 값인지 판단하는 기준은?
    //    equals()의 리턴 값이 true 인지 여부.
    //    hashCode()의 값이 같은지 여부.
    // => 현재 Student 로컬 클래스는 equals()와 hashCode()를 오버라이딩 하지 않았다
    //    따라서 Object에서 상속 받은 그대로이다.
    //    즉 equals()는 인스턴스의 주소가 같은지를 비교한다.
    //    hashCode()는 인스턴스가 다르면 해시 값도 다르다.
    //
    set.add(new Student("aaa", 20));
    set.add(new Student("bbb", 30));
    
    // Set에 들어 있는 값 꺼내기
    // => 직접 값을 한 개씩 꺼내는 메서드가 없다.
    // => 꺼내주는 객체(iterator)의 도움을 받아야 한다.
    //
    Iterator<Student> iterator = set.iterator(); 
    
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
    
    // 출력 결과를 보면 입력 순서로 꺼낼 수 없다.
    // 이유?
    // => 저장할 때 각 인스턴스에 대해 hashCode()의 리턴 값으로 위치를 정하기 때문이다.
    
    System.out.println("-----------------------");
    
    Object[] values = set.toArray();
    for (Object value : values) {
      System.out.println(value);
    }
    
    System.out.println("------------------------");
    
    // 입력한 값의 타입으로 배열을 받고 싶다면?
    // => 파라미터로 넘겨 준 배열이 값을 담을 만큼 크지 않다면, 새 배열을 만들어 리턴한다.
    Student[] values2 = set.toArray(new Student[0]);
    for (Student value : values2) {
      System.out.println(value);
    }
  }

}






