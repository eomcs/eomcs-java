// Java I/O API : FileOutputStream --> BufferedOutputStream --> DataOutputStream
package com.eomcs.io.ex09.step4;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class Exam0110 {

  public static void main(String[] args) throws Exception {

    FileOutputStream fileOut = new FileOutputStream("temp/test7.data");
    BufferedOutputStream bufOut = new BufferedOutputStream(fileOut);
    DataOutputStream out = new DataOutputStream(bufOut);

    Member member = new Member();
    member.name = "AB가각간";
    member.age = 27;
    member.gender = true;

    long start = System.currentTimeMillis();

    for (int i = 0; i < 100000; i++) {
      out.writeUTF(member.name);
      out.writeInt(member.age);
      out.writeBoolean(member.gender);
    }

    long end = System.currentTimeMillis();

    System.out.println(end - start);

    out.close();

    System.out.println("데이터 출력 완료!");

  }

}
