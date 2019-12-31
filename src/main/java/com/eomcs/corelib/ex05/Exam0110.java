// Queue 구현과 사용
package com.eomcs.corelib.ex05;

import com.eomcs.corelib.ex03.LinkedList;

public class Exam0110 {

  public static void main(String[] args) {
    String s1 = new String("aaa");
    String s2 = new String("bbb");
    String s3 = new String("ccc");
    String s4 = new String("ddd");
    String s5 = new String("eee");

    Queue queue = new Queue();
    queue.offer(s1); // aaa,
    print(queue); 
    queue.offer(s2); // aaa, bbb,
    print(queue);
    queue.offer(s3); // aaa, bbb, ccc,
    print(queue);

    System.out.println("==>" + queue.poll()); // bbb, ccc,
    print(queue);
    System.out.println("==>" + queue.poll()); // ccc,
    print(queue);

    queue.offer(s4); // ccc, ddd,
    print(queue);
    queue.offer(s5); // ccc, ddd, eee,
    print(queue);

    String value;
    while ((value = (String) queue.poll()) != null) {
      System.out.println(value);
    }
  }

  static void print(LinkedList list) {
    for (int i = 0; i < list.size(); i++) {
      System.out.print(list.get(i) + ", ");
    }
    System.out.println();
  }    
}





















