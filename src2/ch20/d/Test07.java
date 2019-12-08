// HashMap에서 Iterator를 얻은 후 값을 변경할 때 - 실행 오류 발생!
package ch20.d;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Test07 {
  
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
    
    System.out.println(iterator.next());
    System.out.println("---------------------------");
    
    map.put("bba", new Student("bba", 20));
    map.put("bbc", new Student("bbc", 20));
    
    // Iterator를 사용하는 중에 Set의 값을 변경하면
    // 더이상 Iterator는 유효하지 않게 된다. 
    // 따라서 다음과 같이 Iterator를 사용하려 하면 실행 오류가 발생한다.
    // 해결책?
    // => 값을 변경하면 다시 Iterator를 얻어야 한다.
    // 
    
    // 다음과 같이 key가 들어있는 목록에서 key를 꺼내 줄 Iterator를 다시 리턴 받아야 한다. 
    //
    //iterator = keySet.iterator();
    
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
    
  }
}









