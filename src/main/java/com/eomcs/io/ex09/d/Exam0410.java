// Java I/O API 사용하기 - ObjectOutputStream과 java.io.Serializable 인터페이스
package com.eomcs.io.ex09.d;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Exam0410 {

  public static void main(String[] args) throws Exception {
    FileOutputStream fileOut = new FileOutputStream("temp/test11.data");
    BufferedOutputStream bufOut = new BufferedOutputStream(fileOut);
    ObjectOutputStream out = new ObjectOutputStream(bufOut);

    Member member = new Member();
    member.name = "AB가각간";
    member.age = 27;
    member.gender = true;

    // serialize 하는 Member의 버전은 1280 이다.
    out.writeObject(member);

    out.close();

    System.out.println("출력 완료!");
  }

}

// 용어 정리!
// 1) Serialize(직렬화)
// - 객체(인스턴스) ===> 바이트 배열
// - marshalling 이라고도 부른다.
//
// 2) Deserialize(객체 복원)
// - 바이트 배열 ===> 객체
// - unmarshalling 이라고도 부른다.
//


