// reflection API - 인스턴스 생성하기
package ch27.c;

import java.lang.reflect.Constructor;

public class Test01 {

  public static void main(String[] args) throws Exception {
    Student s = new Student();
    
    //Class<?> clazz = Student.class;
    //Class<?> clazz = Class.forName("ch27.c.Student"); // 위의 코드와 같은 작업을 한다.
    Class<?> clazz = s.getClass();
    
    // 클래스 정보를 가지고 인스턴스 생성하기(deprecated)
    // => 기본 생성자가 있을 때만 가능하다.
    // => 향후 삭제될 메서드이다. 가능한 사용하지 말라!
    Student s1 = (Student) clazz.newInstance();
    s1.setName("홍길동");
    s1.setEmail("hong@test.com");
    s1.setWorking(true);
    s1.setAge(20);
    System.out.println(s1);
    System.out.println("---------------------------");
    
    // 생성자를 통해 인스턴스를 생성하라!
    // => 기본 생성자 꺼내기
    @SuppressWarnings("unchecked")
    Constructor<Student> c1 = (Constructor<Student>) clazz.getConstructor();
    Student s2 = c1.newInstance();
    System.out.println(s2 != null);
    System.out.println("----------------------------");
    
    // => 파라미터를 받는 생성자 꺼내기
    @SuppressWarnings("unchecked")
    Constructor<Student> c2 = 
        (Constructor<Student>) clazz.getConstructor(String.class, String.class);
    
    // 파라미터를 받는 생성자를 실행할 때는 파라미터 값을 생성자에 넘겨줘야 한다.
    Student s3 = c2.newInstance("홍길동", "hong@test.com");
    System.out.println(s3);
  }

}





