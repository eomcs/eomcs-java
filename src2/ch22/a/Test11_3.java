// java.io.File 클래스 : FileFilter 사용하기 III
package ch22.a;

import java.io.File;
import java.io.FileFilter;

public class Test11_3 {

  public static void main(String[] args) throws Exception {
    
    File file = new File(".");
    
    // anonymous class 
    File[] files = file.listFiles(new FileFilter() {
      @Override
      public boolean accept(File pathname) {
        return pathname.isFile() && pathname.getName().endsWith(".txt");
      }
    });
    
    for (File f : files) {
      System.out.printf("%s %12d %s\n", 
          f.isDirectory() ? "d" : "-",
          f.length(),
          f.getName());
    }
    
  }    
}





