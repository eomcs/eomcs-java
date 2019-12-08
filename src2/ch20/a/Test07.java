// java.util.Collection의 forEach() 사용법
package ch20.a;

import java.util.ArrayList;

public class Test07 {
  
  public static void main(String[] args) {
    ArrayList<String> names = new ArrayList<>();
    names.add("홍길동");
    names.add("임꺽정");
    names.add("유관순");
    names.add("안중근");
    names.add("윤봉길");
    names.add("김구");
    
    names.forEach(item -> {
        System.out.println(item);
      });
  }

}




