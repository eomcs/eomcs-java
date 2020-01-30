// DataBufferedOutputStream을 이용하여 객체 출력 - 인스턴스의 값을 출력
package com.eomcs.io.ex07;

public class Exam0210 {

  public static void main(String[] args) throws Exception {

    DataBufferedOutputStream out = new DataBufferedOutputStream("temp/test5.data");

    Member member = new Member();
    member.name = "AB가각간";
    member.age = 27;
    member.gender = true;

    // 인스턴스의 값을 출력하라!
    // 1) 이름 출력
    out.writeUTF(member.name);

    // 2) 나이 출력 (4바이트)
    out.writeInt(member.age);

    // 3) 성별 출력 (1바이트)
    out.writeBoolean(member.gender);

    out.close();

    System.out.println("데이터 출력 완료!");

    // 문제?
    // => DataBufferedOutputStream과 DataOutputStream 클래스는
    //    생성자를 빼고 나머지 코드가 모두 같다.
    // => 안타깝게도 DataOutputStream의 코드를 복사하지 않고
    //   재사용하는 방법이 없다.
    //   이것이 상속으로 기능을 확장했을 때의 한계이다.
    // => 해결책?
    //   바이트 값을 읽어 String,int,boolean 값으로 바꾸는 코드를
    //   장신구(decorator)처럼 붙였다 뗐다 하게 만들라!
  }

}
