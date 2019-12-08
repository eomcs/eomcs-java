// 잘못된 값을 넣었을 때 무시하지 않고 강력하게 오류를 알리기!
package ch12.d;

public class Test03 {

  public static void main(String[] args) {
   
    
    Patient3 p = new Patient3();
    p.setName("김영희");
    p.setAge(20);
    p.setWeight(60);
    p.setHeight(157);
    p.setGender(Patient.WOMAN);

    System.out.println(p);
    
    Patient3 p2 = new Patient3();
    p2.setName("이철희");
    p2.setAge(300); // 캡슐화를 무너뜨릴 수 있는 유효하지 않은 값을 넣으면 오류 발생!
    p2.setWeight(-50); // 캡슐화를 무너뜨릴 수 있는 유효하지 않은 값을 넣으면 오류 발생!
    p2.setHeight(400); // 캡슐화를 무너뜨릴 수 있는 유효하지 않은 값을 넣으면 오류 발생!
    p2.setGender(3); // 캡슐화를 무너뜨릴 수 있는 유효하지 않은 값을 넣으면 오류 발생!

    System.out.println(p2);
    
  }

}





