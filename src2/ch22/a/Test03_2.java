// java.io.File 클래스 : 디렉토리 생성
package ch22.a;

import java.io.File;

public class Test03_2 {

  public static void main(String[] args) throws Exception {
    
    // 존재하지 않는 폴더 아래에 새 폴더를 만들 때 
    // => 존재하지 않는 폴더가 자동 생성되지 않는다. 
    // => 따라서 그 하위 폴더를 생성할 수 없다.
    //
    File dir = new File("temp2/ok");
    if (dir.mkdir()) {
      System.out.println("디렉토리 생성됨.");
    } else {
      System.out.println("디렉토리 생성 못함.");
    }
 
  }

}





