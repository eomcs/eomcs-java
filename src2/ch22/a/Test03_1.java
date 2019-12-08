// java.io.File 클래스 : 디렉토리 생성
package ch22.a;

import java.io.File;

public class Test03_1 {

  public static void main(String[] args) throws Exception {
    
    // 생성할 디렉토리 정보를 준비한다.
    File dir = new File("temp");
    if (dir.mkdir()) {
      System.out.println("디렉토리 생성됨.");
    } else {
      System.out.println("디렉토리 생성 못함.");
    }

  }

}





