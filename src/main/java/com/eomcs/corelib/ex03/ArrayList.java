package com.eomcs.corelib.ex03;

public class ArrayList {
  protected static final int DEFAULT_CAPACITY = 5;

  Object[] list;
  int cursor;

  public ArrayList() {
    // 자신의 다른 생성자를 호출할 수 있다.
    // => 생성자를 호출할 수 있는 경우
    //    1) new 명령을 사용할 때
    //    2) 생성자에서 다른 생성자를 호출할 때
    //    그 외에는 임의로 생성자를 호출할 수 없다.
    // 일반 메서드에서 생성자를 임의로 호출할 수 없다.
    // 다른 생성자를 호출할 때는 super 클래스의 생성자를 호출할 수 없다.
    // => 둘 중 하나만 첫 문장으로 올 수 있다.
    //super(); // 컴파일 오류!
    this(DEFAULT_CAPACITY);
  }

  public ArrayList(int capacity) {
    // super(); 항상 생성자에는 수퍼 클래스의 기본 생성자를 호출하는 문장이 숨겨져 있다.
    //          물론 개발자가 수퍼 클래스의 생성자를 호출하겠다고 명시한다면 
    //          당연히 자동으로 붙지 않는다.
    if (capacity < DEFAULT_CAPACITY) {
      list = new Object[DEFAULT_CAPACITY];
    } else {
      list = new Object[capacity];
    }
  }

  public void add(Object value) {
    if (cursor >= list.length) {
      this.increaseArray();
    }
    list[cursor++] = value;
  }

  public Object get(int index) {
    return list[index];
  }

  public void set(int index, Object value) {
    list[index] = value;
  }

  public void remove(int index) {
    if (index < 0 || index >= cursor)
      return;

    for (int i = index + 1; i < cursor; i++) {
      list[i-1] = list[i];
    }
    cursor--;
  }

  public void add(int index, Object value) {
    if (index < 0 || index > cursor)
      return;

    if (cursor >= list.length) {
      this.increaseArray();
    }

    for (int i = cursor - 1; i >= index; i--) {
      list[i + 1] = list[i];
    }
    list[index] = value;
    cursor++;
  }

  private void increaseArray() {
    Object[] list2 = new Object[list.length + DEFAULT_CAPACITY];
    for (int i = 0; i < list.length; i++) {
      list2[i] = list[i];
    }
    list = list2;
  }

  public int size() {
    return cursor;
  }

  public boolean contains(Object value) {
    for (Object obj : list) {
      if (obj.equals(value)) // 같은 객체가 들어 있는지 검사하는 것이 아니다.
        return true;       // 같은 내용을 가진 객체가 들어 있는지 검사하는 것이다.
    }
    return false;
  }

  public int indexOf(Object value) {
    for (int i = 0; i < list.length; i++) {
      if (list[i].equals(value)) // 같은 객체가 들어 있는지 검사하는 것이 아니다.
        return i;          // 같은 내용을 가진 객체가 들어 있는지 검사하는 것이다.
    }
    return -1;
  }
}








