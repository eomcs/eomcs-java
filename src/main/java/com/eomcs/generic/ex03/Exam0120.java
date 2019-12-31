package com.eomcs.generic.ex03;

import java.lang.reflect.Array;

public class Exam0120<T extends Member> {

  T[] arr;
  int index = 0;

  public Exam0120(Class<?> clazz) {

    //final T[] arr0 = new T[10]; // 컴파일 오류!

    @SuppressWarnings("unchecked")
    final T[] arr = (T[])Array.newInstance(clazz, 10);
    this.arr = arr;
  }

  public void add(T v) {
    arr[index++] = v;
  }

  public T get(int index) {
    return arr[index];
  }

  public static void main(String[] args) {

    Exam0120<Member> obj = new Exam0120<>(Member.class);
    obj.add(new Member());
    obj.add(new Student());
    obj.add(new Teacher());
    obj.add(new Manager());

    System.out.println(obj.get(0));
    System.out.println(obj.get(1));
    System.out.println(obj.get(2));
    System.out.println(obj.get(3));
  }

}
