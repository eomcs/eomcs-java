package ch19.g;

import java.io.File;
import java.io.FilenameFilter;

// File.list() 메서드에서 사용할 FilenameFilter 를 만든다.
//
public class MyFilenameFilter implements FilenameFilter {

  @Override
  public boolean accept(File dir, String name) {
    if (name.endsWith(".txt"))
      return true;
    else 
      return false;
  }

}










