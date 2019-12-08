// java.io.File 클래스 : 파일 생성
package ch22.a;

import java.io.File;

public class Test05_1 {

  public static void main(String[] args) throws Exception {
    
    // 존재하지 않는 파일을 생성함.
    // => 이미 파일이 존재한다면 생성 못함.
    File file = new File("temp2/test.txt");
    if (file.createNewFile()) {
      System.out.println("파일이 생성됨.");
    } else {
      System.out.println("파일을 생성하지 못함.");
    }
  }
}





