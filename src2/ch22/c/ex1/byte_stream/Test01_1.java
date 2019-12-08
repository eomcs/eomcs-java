// 버퍼 사용 - 사용 전
// 
package ch22.c.ex1.byte_stream;

import java.io.FileInputStream;

public class Test01_1 {
  public static void main(String[] args) throws Exception {

    FileInputStream in = new FileInputStream("temp/jls12.pdf");

    System.out.println("데이터 읽는 중...");

    long start = System.currentTimeMillis();

    int b;
    int count = 0;
    while ((b = in.read()) != -1) {
      count++;
      // 1바이트를 읽었으면 암호화한다고 가정하자!
      // 그러면 여기에 그 암호화하는 코드를 작성해야 한다.
    }

    long end = System.currentTimeMillis();
    System.out.println(end - start);
    System.out.println(count);

    in.close();

    System.out.println("출력 완료!");
  }
}








