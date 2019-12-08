// 문자 배열 읽기
package ch22.b.character_stream;

import java.io.FileReader;

public class Test02_2 {

  public static void main(String[] args) throws Exception {

    // FileReader
    // => 문자 단위로 데이터를 읽는 일을 한다.
    //
    FileReader in = new FileReader("data.txt");

    char[] cbuf = new char[1024];

    int len = in.read(cbuf);

    System.out.println(len);
    for (int i = 0; i < len; i++) {
      System.out.print(cbuf[i] + ",");
    }
    System.out.println();

    in.close();

    System.out.println("읽기 완료!");
  }

}
