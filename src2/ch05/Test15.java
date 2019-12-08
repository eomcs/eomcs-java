// 흐름제어문 - for 반복문 IV
package ch05;

public class Test15 {

  public static void main(String[] args) {
    String[] names = {"홍길동", "임꺽정", "유관순", "안중근", "윤봉길", "김구"};
    
    // 배열을 처음부터 끝까지 반복할 때는 다음의 for 문을 사용하면 편리하다.
    for (String name : names) {
      System.out.print(name + " ");
    }
    System.out.println();
    
    // 컬렉션 객체 사용
    java.util.ArrayList<String> list = new java.util.ArrayList<>();
    list.add("홍길동");
    list.add("임꺽정");
    list.add("유관순");
    
    for (String name : list) {
      System.out.print(name + " ");
    }
    System.out.println();
  }
}

/*
# for (:)
- 배열 전체를 반복하거나 컬렉션(collection) 객체 전체를 반복할 때 유용한다.
- 배열의 일부만 반복할 수 없다.
- 배열의 값을 다룰 때 인덱스를 사용할 필요가 없어 편리하다.

  for (배열의 항목 값을 담을 변수 선언 : 배열, Collection 객체 등)
    문장1;
 */














