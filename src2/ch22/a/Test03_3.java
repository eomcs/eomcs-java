// java.io.File 클래스 : 디렉토리 생성
package ch22.a;

import java.io.File;

public class Test03_3 {

  public static void main(String[] args) throws Exception {
    
    // 존재하지 않는 상위 폴더를 자동으로 생성하는 방법
    // => mkdirs()
    //
    File dir = new File("temp2/aaa/ok");
    if (dir.mkdirs()) {
      System.out.println("디렉토리 생성됨.");
    } else {
      System.out.println("디렉토리 생성 못함.");
    }
  }

}





