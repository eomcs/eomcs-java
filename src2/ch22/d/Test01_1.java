// String 출력하기
package ch22.d;

import java.io.FileWriter;

public class Test01_1 {

  public static void main(String[] args) throws Exception {

    // FileWriter
    // => 문자 단위로 데이터를 출력하는 일을 한다.
    //
    FileWriter out = new FileWriter("temp/data.txt");

    out.write("ABC가각간012#!?");

    out.close();

    System.out.println("출력 완료!");
  }

}
