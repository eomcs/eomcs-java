// Java I/O API 사용하기 - ObjectOutputStream과 java.io.Serializable 인터페이스
package com.eomcs.io.ex10;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Exam0220 {

  public static void main(String[] args) throws Exception {
    FileOutputStream fileOut = new FileOutputStream("temp/test9_3.data");
    BufferedOutputStream bufOut = new BufferedOutputStream(fileOut);
    ObjectOutputStream out = new ObjectOutputStream(bufOut);

    Member2 member = new Member2();
    member.name = "AB가각간";
    member.age = 27;
    member.gender = true;

    // ObjectOutputStream에는 인스턴스의 값을 바이트 배열로 만들어 출력하는 기능이 있다.
    out.writeObject(member);
    // Member2 클래스는 java.io.Serializable을 구현했기 때문에
    // 바이트 배열로 자동 변환하는 것이 가능하다.
    // 그래서 writeObject()를 호출하여 인스턴스의 값을 출력할 수 있다.
    // 
    // writeObject()는 인스턴스의 값만 출력하는 것이 아니라,
    // 인스턴스의 클래스 이름과 인스턴 변수의 타입 정보도 함께 출력한다.
    // => 출력데이터 = 클래스이름 + 인스턴스 변수 정보 + 인스턴스 변수 값

    out.close();
  }

}

// 용어 정리!
// Serialize   : 객체 ===> 바이트 배열   (marshalling 이라고도 부른다.)
// Deserialize : 바이트 배열 ===> 객체   (unmarshalling 이라고도 부른다.)
// 









