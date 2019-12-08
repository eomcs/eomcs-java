// 예외 처리 연습 - 예외처리를 호출자에게 넘기기
package ch21.g;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Test01_3 {

  public static void main(String[] args) throws FileNotFoundException {
    
    FileReader in;
    
    in = new FileReader("build.gradle");
    // 여기서 예외가 발생하면 main()의 호출자인 JVM에게 전달될 것이다.
  }

}
