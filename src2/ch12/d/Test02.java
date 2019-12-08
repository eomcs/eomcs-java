// 캡슐화(encapsulation) : 적용 후
package ch12.d;

public class Test02 {

  public static void main(String[] args) {
   
    
    Patient2 p = new Patient2();
    p.setName("김영희");
    p.setAge(20);
    p.setWeight(60);
    p.setHeight(157);
    p.setGender(Patient.WOMAN);

    System.out.println(p);
    
    Patient2 p2 = new Patient2();
    p2.setName("이철희");
    p2.setAge(300); // 캡슐화를 무너뜨릴 수 있는 유효하지 않은 값은 무시된다.
    p2.setWeight(-50); // 캡슐화를 무너뜨릴 수 있는 유효하지 않은 값은 무시된다.
    p2.setHeight(400); // 캡슐화를 무너뜨릴 수 있는 유효하지 않은 값은 무시된다.
    p2.setGender(3); // 캡슐화를 무너뜨릴 수 있는 유효하지 않은 값은 무시된다.

    System.out.println(p2);
    
  }

}





