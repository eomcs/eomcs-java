// java.io.File 클래스 : 필터와 Lambda 표현식을 사용하여 디렉토리 이름만 추출하라. 
package ch22.a;

import java.io.File;

public class Test12 {

  public static void main(String[] args) throws Exception {
    
    File file = new File(".");
    
    File[] files = file.listFiles(f -> f.isDirectory());
    
    for (File f : files) {
      System.out.printf("%s %12d %s\n", 
          f.isDirectory() ? "d" : "-",
          f.length(),
          f.getName());
    }
    
  }    
}





