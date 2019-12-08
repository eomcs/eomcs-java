// 파일 필터 클래스를 중첩 클래스로 만든다.
package ch19.g;

import java.io.File;
import java.io.FilenameFilter;

public class Test03 {

  // 이 클래스 내부에서만 사용할 클래스라면 이렇게 중첩 클래스로 만드는 것이 유지보수하기 좋다.
  //
  public static class MyFilenameFilter implements FilenameFilter {
    @Override
    public boolean accept(File dir, String name) {
      if (name.endsWith(".txt"))
        return true;
      else 
        return false;
    }
  }
  
  public static void main(String[] args) {
    File dir = new File("./"); // 이클립스에서 실행하면 ./ 는 프로젝트 디렉토리를 가리킨다.
    
    // 필터 클래스가 같은 클래스에 있어 무슨 일을 하는지 소스를 살펴보기 편하다.
    //
    String[] names = dir.list(new MyFilenameFilter());
    // 
    // 만약 main() 메서드와 필터를 정의한 클래스 사이에 많은 코드가 있다면
    // 이전과 마찬가지로 역시 소스 코드를 살펴보기가 불편하다.
    
    for (String name : names) {
      System.out.println(name);
    }

  }

}
