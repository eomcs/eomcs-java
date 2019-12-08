// 파일 필터 클래스를 익명 클래스로 만든다. 그 자리에!
package ch19.g;

import java.io.File;
import java.io.FilenameFilter;

public class Test06 {

  
  public static void main(String[] args) {
    File dir = new File("./"); // 이클립스에서 실행하면 ./ 는 프로젝트 디렉토리를 가리킨다.
    
    // 익명 클래스는 어차피 단 한 개의 인스턴스만 만들 수 있다.
    // 그렇다면 레퍼런스에 따로 인스턴스 주소를 저장하지 말고,
    // 필요한 곳에 바로 선언하자!
    //
    String[] names = dir.list(new FilenameFilter() {
      @Override
      public boolean accept(File dir, String name) {
        if (name.endsWith(".txt"))
          return true;
        else 
          return false;
      }
    });
    // 
    // 필터가 무슨일을 하는지 바로 알 수 있다!!!
    // => 필터의 소스 코드를 확인하기 위해 돌아 다닐 필요가 없다.
    // => 단 코드의 길이 짧은 경우에 이 방식이 보기 좋다.
    // => 코드가 너무 길면 어디서 어디까지가 중첩 클래스인지 가늠하기 힘들다.
    
    for (String name : names) {
      System.out.println(name);
    }

  }

}
