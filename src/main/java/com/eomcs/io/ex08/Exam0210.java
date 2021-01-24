// 포함 관계로 기능 확장하기 - FileOutputStream + DataOutputStream
package com.eomcs.io.ex08;

import java.io.FileOutputStream;

public class Exam0210 {

  public static void main(String[] args) throws Exception {

    // FileInputStream
    // - 파일 저장소에서 데이터를 읽는 일을 한다.
    FileOutputStream out1 = new FileOutputStream("temp/member.data");

    // FileInputStream + DataInputStream
    // - 문자열이나 자바 기본 타입의 데이터를 좀 더 쉽게 읽기
    // - 그러나 안타깝게도 이런 식으로 기능을 확장할 수 없다.
    //   왜?
    //   - DataInputStream 생성자에는 InputStream 객체만 넘겨줄 수 있다.
    //   - 즉 DataInputStream은 InputStream 객체에만 연결할 수 있다.
    //   - BufferedInputStream은 InputStream 의 자식이 아니기 때문에
    //     DataInputStream에 연결할 수 없다.
    DataOutputStream out2 = new DataOutputStream(out1);

    Member member = new Member();
    member.name = "AB가각간";
    member.age = 27;
    member.gender = true;

    out2.writeUTF(member.name);
    out2.writeInt(member.age);
    out2.writeBoolean(member.gender);

    out2.close();

    System.out.println("출력 완료!");
  }

}
