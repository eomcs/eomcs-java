// String 읽기 - BufferedReader 붙이기
package ch22.d;

import java.io.BufferedReader;
import java.io.FileReader;

public class Test01_2 {

  public static void main(String[] args) throws Exception {

    // FileReader
    // => 문자 단위로 데이터를 읽는 일을 한다.
    //
    FileReader in = new FileReader("data.txt");
    
    // 데코레이터를 붙인다. 
    // => 버퍼 기능 + 한 줄 읽기 기능
    BufferedReader in2 = new BufferedReader(in);

    System.out.println(in2.readLine());

    in.close();

    System.out.println("읽기 완료!");
  }

}
