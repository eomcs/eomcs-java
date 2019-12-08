// java.io.File 클래스 : 필터 사용하기 III
package ch22.a;

import java.io.File;
import java.io.FilenameFilter;

public class Test10_3 {

  public static void main(String[] args) throws Exception {
    
    File file = new File(".");
    
    // anonymous class 
    String[] names = file.list(new FilenameFilter() {
      @Override
      public boolean accept(File dir, String name) {
        return name.endsWith(".txt");
      }
    });
    
    for (String name : names) {
      System.out.println(name);
    }
    
  }    
}









