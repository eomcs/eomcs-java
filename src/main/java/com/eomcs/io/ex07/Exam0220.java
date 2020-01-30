// DataBufferedInputStream으로 객체 읽기 - 파일이 데이터를 읽어 인스턴스로 만들기
package com.eomcs.io.ex07;

public class Exam0220 {

  public static void main(String[] args) throws Exception {

    DataBufferedInputStream in = new DataBufferedInputStream("temp/test5.data");

    Member member = null;

    member = new Member();

    // 1) 이름 읽기
    member.name = in.readUTF();

    // 2) 나이(int) 읽기
    member.age = in.readInt();

    // 3) 성별 읽기
    member.gender = in.readBoolean();

    in.close();

    System.out.printf("%s\n", member);
    
    // 문제?
    // => DataBufferedInputStream과 DataInputStream 클래스는
    //    생성자를 빼고 나머지 코드가 모두 같다.
    // => 안타깝게도 DataInputStream의 코드를 복사하지 않고
    //   재사용하는 방법이 없다.
    //   이것이 상속으로 기능을 확장했을 때의 한계이다.
    // => 해결책?
    //   바이트 값을 읽어 String,int,boolean 값으로 바꾸는 코드를
    //   장신구(decorator)처럼 붙였다 뗐다 하게 만들라!
  }
}


