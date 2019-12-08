// java.io.File 클래스 : 폴더 안에 있는 파일이나 하위 폴더를 알아내기
package ch22.a;

import java.io.File;

public class Test08 {

  public static void main(String[] args) throws Exception {
    
    // 파일은 디렉토리와 파일을 통칭하는 용어다.
    File file = new File(".");
    
    // 하위 파일이나 디렉토리 이름 알아내기
    String[] names = file.list();
    for (String name : names) {
      System.out.println(name);
    }
    
  }    
}





