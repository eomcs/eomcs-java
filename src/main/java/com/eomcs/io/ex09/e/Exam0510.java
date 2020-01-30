// Java I/O API 사용하기 - serialize와 transient 변경자
package com.eomcs.io.ex09.e;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Exam0510 {

  public static void main(String[] args) throws Exception {
    FileOutputStream fileOut = new FileOutputStream("temp/test12.data");
    BufferedOutputStream bufOut = new BufferedOutputStream(fileOut);
    ObjectOutputStream out = new ObjectOutputStream(bufOut);

    Score s = new Score();
    s.name = "홍길동";
    s.kor = 99;
    s.eng = 80;
    s.math = 92;
    s.compute();

    // serialize 할 때 transient가 붙은 필드의 값은 제외된다.
    out.writeObject(s);

    out.close();
    System.out.println("출력 완료!");
  }

}


