// DataOutputStream을 이용하여 객체 출력 - 인스턴스의 값을 출력
package com.eomcs.io.ex08;

import java.io.FileOutputStream;

public class Exam0110 {

  public static void main(String[] args) throws Exception {
    FileOutputStream fileOut = new FileOutputStream("temp/test7.data");

    // 기존의 FileOutputStream 객체에 String,int,boolean 값을 출력하는
    // 장신구/보조장치/플러그인(decorator)을 장착한다.
    DataOutputStream out = new DataOutputStream(fileOut);

    Member member = new Member();
    member.name = "AB가각간";
    member.age = 27;
    member.gender = true;

    // 장신구/보조장치/플러그인(decorator)를 사용하여 String,int,boolean 값을 출력한다.
    out.writeUTF(member.name); 
    out.writeInt(member.age);
    out.writeBoolean(member.gender);

    out.close();

    System.out.println("데이터 출력 완료!");

  }

}
