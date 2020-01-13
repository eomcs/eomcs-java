package com.eomcs.algorithm.data_structure.stack;

import java.util.Arrays;

public class Stack<E> implements Cloneable {
  
  private static final int DEFAULT_CAPACITY = 10;
  
  Object[] elementData;
  int size;
  
  public Stack() {
    this.elementData = new Object[DEFAULT_CAPACITY];
    this.size = 0;
  }
  
  public void push(E value) {
    if (this.size == elementData.length) {
      grow();
    }
    this.elementData[size++] = value;
  }
  
  private void grow() {
    this.elementData = Arrays.copyOf(elementData, newCapacity());
  }
  
  private int newCapacity() {
    int oldCapacity = elementData.length;
    return oldCapacity + (oldCapacity >> 1);
  }
  
  @SuppressWarnings("unchecked")
  public E pop() {
    if (this.empty())
      return null;
    E value = (E) this.elementData[--this.size];
    this.elementData[this.size] = null;
    return value;
  }
  
  public boolean empty() {
    return this.size == 0;
  }
  
  // Object.clone()의 'shallow copy' 이용하여 스택 객체 복사하기
  // => 객체의 인스턴스 변수를 그대로 복제한다.
  // => 인스턴스 변수가 가리키는 객체는 복제하지 않는다.
  // 
  // 문제점?
  // => 따라서 인스턴스 변수인 elementData가 가리키는 배열은 복제하지 않는다.
  // => 그래서 배열의 값을 배꾸면 원본 스택에도 영향을 끼친다.
  //
  /*
  @Override
  public Stack clone() {
    try {
      return (Stack)super.clone();
      
    } catch (CloneNotSupportedException ex) {
      // Object의 clone() 메서드는 
      // 복제가 허용된 객체에 대해서만 해당 인스턴스 변수를 복제한다.
      // 복제가 허용되지 않은 객체에 대해서 clone()을 호출하면 
      // CloneNotSupportedException 실행 오류가 발생한다.
      // 해결책?
      // => 해당 클래스의 객체를 복제할 수 있다고 표시하라. 
      // => 방법: Cloneable 인터페이스를 지정한다.
      //    예) class My implements Cloneable {...}
      //
      System.out.println(ex);
      return null;
    }
  }
  */
  
  // 'deep copy'를 이용하여 객체 복제하기
  // => 데이터가 들어있는 배열도 그대로 복제한다.
  // => 따라서 배열의 값을 바꾸더라도 원본 객체에 영향을 끼치지 않는다.
  //
  
  @SuppressWarnings("unchecked")
  @Override
  public Stack<E> clone() {
    try {
      //1) 'shallow copy'를 통해 이 객체의 인스턴스 변수는 그대로 복제한다. 
      Stack<E> temp = (Stack<E>) super.clone();
      
      //2) elementData 배열을 복제한다.
      // => 배열만 복제하고 그 배열에 저장된 객체(ex: 문자열, Member 등)까지는 복제하지 않는다.
      // => 어디까지 복제(deep copy의 수준)할 것인지는 상황에 따라 결정한다.
      //
      Object[] arr = new Object[this.size];
      for (int i = 0; i < this.size; i++) {
        arr[i] = this.elementData[i];
      }
      
      //3) 복제한 스택 객체가 새로 만든 배열을 가리키도록 한다.  
      temp.elementData = arr;
      
      return temp;
      
    } catch (CloneNotSupportedException ex) {
      System.out.println(ex);
      return null;
    }
  }
}




