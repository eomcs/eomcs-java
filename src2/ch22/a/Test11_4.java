// java.io.File 클래스 : FileFilter 사용하기 IV
package ch22.a;

import java.io.File;

public class Test11_4 {

  public static void main(String[] args) throws Exception {
    
    File file = new File(".");
    
    // anonymous class 
    File[] files = file.listFiles(pathname -> 
        pathname.isFile() && pathname.getName().endsWith(".txt")
    );
    
    for (File f : files) {
      System.out.printf("%s %12d %s\n", 
          f.isDirectory() ? "d" : "-",
          f.length(),
          f.getName());
    }
    
  }    
}





