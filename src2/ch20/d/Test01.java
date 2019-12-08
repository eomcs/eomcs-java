// Map 클래스 사용법 - put(), get()
package ch20.d;

import java.util.HashMap;

public class Test01 {
  
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
    
    // java.util.Map 구현체
    // => HashMap, Hashtable 이 있다.
    // => key를 가지고 value을 저장하고 꺼낸다.
    // 
    
    HashMap<String, Student> map = new HashMap<>();
    
    // 맵에 값 저장하기
    // => key 객체에 대해 hashCode()를 호출하여 정수 값을 얻는다.
    // => 이 해시값을 가지고 저장할 위치를 결정한다.
    //
    map.put("aaa", new Student("홍길동", 20));
    // 1) "aaa" String 객체에 대해 hashCode()를 호출하여 해시 값을 얻는다.
    // 2) 그 해시 값을 사용하여 저장할 위치를 결정한다.
    // 3) 해당 위치에 Student 객체(의 주소)를 저장한다.
    
    map.put("bbb", new Student("임꺽정", 30));
    map.put("ccc", new Student("유관순", 16));
    
    // 같은 key로 값을 저장하면 기존의 값을 덮어 쓴다.
    map.put(new String("ccc"), new Student("안중근", 25));
    
    // 맵에서 값 꺼내기
    // => 저장할 때 사용한 키를 가지고 꺼낸다.
    System.out.println(map.get("bbb"));
    // 1) key 객체에 대해 hashCode()를 호출한다.
    // 2) hashCode()의 리턴 값을 가지고 데이터를 찾을 위치를 결정한다.
    // 3) 해당 위치에 있는 key 객체에 대해 equals()를 호출하여 리턴값을 확인한다.
    // 4) equals()의 리턴 값이 true라면 같은 key로 간주하여 해당 위치의 값을 꺼낸다.
    
    String key1 = "bbb";
    String key2 = new String ("bbb");
    System.out.println(key1 == key2);  // 인스턴스가 다르다.
    System.out.println(key1.hashCode() == key2.hashCode()); // 해시 값은 같다.
    System.out.println(key1.equals(key2)); // 리턴 값이 true이다.
    // 결론: key1과 key2는 서로 다른 인스턴스이지만, hashCode()의 리턴 값이 같고,
    //      equals()의 리턴 값이 trud이므로 두 인스턴스는 같은 key로 간주한다.
    // 따라서 key1으로 저장된 값을 key2로 꺼낼 수 있다.
    System.out.println(map.get(key2));
    
    System.out.println(map.get("ccc"));
  }
}









