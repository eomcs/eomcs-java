// HashMap에서 Iterator 사용하기
package ch20.d;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Test06 {
  
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
    
    HashMap<String, Student> map = new HashMap<>();
    map.put("aaa", new Student("홍길동", 20));
    map.put("bbb", new Student("임꺽정", 30));
    map.put("ccc", new Student("안중근", 25));
    
    Set<String> keySet = map.keySet();
    
    // Set에서 값을 꺼내기 위해 Iterator의 도움을 받는다.
    Iterator<String> iterator = keySet.iterator();
    
    // Iterator를 통해 키 목록에서 값을 한 개씩 꺼낸다.
    System.out.println(iterator.next());
    System.out.println(iterator.next());
    System.out.println(iterator.next());
    
    // 키 목록에서 값을 모두 꺼낸 다음에 또 꺼내려한다면 실행 오류 발생!
    //System.out.println(iterator.next()); // runtime 오류!
    
  }
}









