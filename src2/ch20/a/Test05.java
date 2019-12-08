// java.util.ArrayList 사용법 - indexOf()
package ch20.a;

import java.util.ArrayList;

public class Test05 {
  
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

      /*
      @Override
      public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + age;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
      }
      */
      
      @Override
      public boolean equals(Object obj) {
        if (this == obj)
          return true;
        if (obj == null)
          return false;
        if (getClass() != obj.getClass())
          return false;
        Student other = (Student) obj;
        if (age != other.age)
          return false;
        if (name == null) {
          if (other.name != null)
            return false;
        } else if (!name.equals(other.name))
          return false;
        return true;
      }
    }
    
    Student s1 = new Student("aaa", 20);
    Student s2 = new Student("bbb", 30);
    Student s3 = new Student("ccc", 40);
    
    Student s4 = new Student("bbb", 30);
    
    System.out.println(s2 == s4);
    System.out.println(s2.equals(s4));
    System.out.println(s2.hashCode() == s4.hashCode());
    
    ArrayList<Student> list = new ArrayList<>();
    list.add(s1);
    list.add(s2);
    list.add(s3);
    
    
    System.out.println(list);
    
    // indexOf(값) 
    // => 목록에 해당 값이 있는 인덱스를 알아낸다.
    // => 값을 비교할 때는 contains()와 마찬가지로 equals()의 리턴 값이 true인 경우 같은 값으로 간주한다.
    System.out.println(list.indexOf(s2)); // => 1
    System.out.println(list.indexOf(s4)); // => s2.equals(s4)의 리턴 값이 true 이면 1 을 리턴.
    System.out.println(list.indexOf(new Student("ccc", 30))); // 못찾으면 -1 이다.
  }

}




