// java.util.ArrayList 사용법 - 메서드 사용
package ch20.a;

import java.util.ArrayList;

public class Test02 {

  public static void main(String[] args) {
    
    ArrayList<String> list = new ArrayList<>();
    
    // add(value) : 목록에 순차적으로 추가한다.
    list.add("aaa");
    list.add("bbb");
    list.add("ccc");
    list.add("ddd");
    list.add(null); // ArrayList는 null을 추가하는 것을 허용한다.
    list.add("eee");
    list.add(null); // ArrayList는 null을 추가하는 것을 허용한다.
    list.add("aaa"); // ArrayList는 같은 인스턴스를 중복해서 추가할 수 있다. 
    list.add("bbb"); // ArrayList는 같은 인스턴스를 중복해서 추가할 수 있다.
    
    System.out.println(list);
    
    // remove(index) : 목록에서 해당 인덱스의 값을 삭제한다. 리턴 값은 삭제된 값이다.
    String s = list.remove(2);
    System.out.println(list);
    System.out.println(s);
    
    // set(index, value) : 해당 위치의 값을 변경한다.
    list.set(2, "xxx");
    System.out.println(list);

    // get(index) : 해당 위치의 값을 리턴한다.
    System.out.println(list.get(2));
    System.out.println(list.get(3));
    
    // 인덱스가 유효하지 않을 때 IndexOutOfBoundsException 예외가 발생한다.
    System.out.println(list.get(100));
  }

}




