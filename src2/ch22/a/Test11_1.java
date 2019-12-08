// java.io.File 클래스 : FileFilter 사용하기
package ch22.a;

import java.io.File;
import java.io.FileFilter;

public class Test11_1 {

  // static nested class 
  static class TextFileFilter implements FileFilter {
    @Override
    public boolean accept(File file) {
      return file.isFile() && file.getName().endsWith(".txt");
    }
  }
  
  public static void main(String[] args) throws Exception {
    
    File file = new File(".");
    
    File[] files = file.listFiles(new TextFileFilter());
    
    for (File f : files) {
      System.out.printf("%s %12d %s\n", 
          f.isDirectory() ? "d" : "-",
          f.length(),
          f.getName());
    }
    
  }    
}





