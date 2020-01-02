// Java I/O API 사용하기 - java.io.Serializable 인터페이스와 serialVersionUID 변수
package com.eomcs.io.ex10;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Exam0310 {

  public static void main(String[] args) throws Exception {
    FileOutputStream fileOut = new FileOutputStream("temp/test9_4.data");
    BufferedOutputStream bufOut = new BufferedOutputStream(fileOut);
    ObjectOutputStream out = new ObjectOutputStream(bufOut);

    Member3 member = new Member3();
    member.name = "AB가각간";
    member.age = 27;
    member.gender = true;

    out.writeObject(member);
    // Member3 객체를 serialize 하여 바이트 배열로 출력할 때 
    // 버전 번호도 함께 출력된다. 
    // Member3의 버전 번호는 100이다. 

    out.close();
  }

}

// 용어 정리!
// Serialize   : 객체 ===> 바이트 배열   (marshalling 이라고도 부른다.)
// Deserialize : 바이트 배열 ===> 객체   (unmarshalling 이라고도 부른다.)
// 









