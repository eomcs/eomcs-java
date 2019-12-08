// java.util.ArrayList 사용법 - 제네릭 설정
package ch20.a;

import java.util.ArrayList;
import java.util.Date;

public class Test01 {

  public static void main(String[] args) {
    
    // 자바 컬렉션 API (collection API)
    // => 자바에서 데이터 목록을 다루는 클래스를 말한다.
    // => java.util.Collection 인터페이스를 구현한 객체이다.
    //
    // ArrayList에 보관할 데이터의 타입을 지정하지 않으면 모든 타입의 인스턴스(주소)를 저장할 수 있다.
    ArrayList list = new ArrayList();
    list.add("Hello");
    list.add(Integer.valueOf(100));
    list.add(100);
    list.add(new Date());
    
    // ArrayList는 제네릭이 적용되어 있기 때문에 모든 타입의 인스턴스를 저장하고 싶다면,
    // 제네릭을 사용하여 ArrayList를 만들라!
    ArrayList<Object> list2 = new ArrayList<>();
    list2.add("Hello");
    list2.add(Integer.valueOf(100));
    list2.add(100);
    list2.add(new Date());

  }

}




