// java.util.ArrayList와 비교
package com.eomcs.corelib.ex03;

import java.util.ArrayList;

public class Exam0120 {
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

    // 유효한 인덱스가 아니면 예외 발생시킨다!
    //list.remove(4); // 실행 오류!

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






