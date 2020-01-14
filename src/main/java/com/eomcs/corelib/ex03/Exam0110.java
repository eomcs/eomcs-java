// 객체(의 주소) 목록을 다루는 클래스 - 컬렉션 클래스(collection class) 정의
package com.eomcs.corelib.ex03;

// 배열을 이용하여 컬렉션 클래스 만들기 : ArrayList
// => 단점: 배열의 크기가 고정되기 때문에 배열을 초과하여 값을 넣으려면
//         더 큰 새 배열을 만들고, 기존 값을 복사해야 한다.
//         배열의 크기가 늘 때마다 가비지(garbage)가 생기는 문제가 있다.
//         기본 배열의 값을 복사하기 때문에 속도가 느린 문제도 있다.
public class Exam0110 {
  public static void main(String[] args) {
    ArrayList list = new ArrayList();
    list.add("aaa"); // aaa,
    list.add("bbb"); // aaa, bbb,
    list.add("ccc"); // aaa, bbb, ccc,
    list.add("ddd"); // aaa, bbb, ccc, ddd,

    print(list); 

    list.remove(2); // aaa, bbb, ddd,

    print(list); 

    list.remove(0); // bbb, ddd,

    print(list);

    list.remove(4); // bbb, ddd,

    print(list);

    list.add(1, "xxx"); // bbb, xxx, ddd,
    list.add(1, "yyy"); // bbb, yyy, xxx, ddd
    list.add(0, "zzz"); // zzz, bbb, yyy, xxx, ddd
    list.add(5, "ttt"); // zzz, bbb, yyy, xxx, ddd

    print(list);

    list.set(1, "aaa"); // zzz, aaa, yyy, xxx, ddd

    print(list);

    list.add("ccc"); // zzz, aaa, yyy, xxx, ddd, ccc
    list.add("eee"); // zzz, aaa, yyy, xxx, ddd, ccc, eee

    print(list);
  }

  static void print(ArrayList list) {
    for (int i = 0; i < list.size(); i++) {
      System.out.print(list.get(i) + ", ");
    }
    System.out.println();
  }
}   






