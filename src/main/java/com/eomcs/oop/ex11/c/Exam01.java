// non-static nested class == inner class
package com.eomcs.oop.ex11.c;

public class Exam01 {

    int value;

    // => 인스턴스 메서드에서는 인스턴스 변수에 접근할 수 있다.
    // => 인스턴스 메서드는 this라는 내장 변수에 호출될 때 넘겨받은 
    //    인스턴스 주소를 보관한다.
    //
    public void setValue(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static void main(String[] args) {
        Exam01 obj = new Exam01();
        obj.setValue(100);
        
        System.out.println(obj.getValue());
    }

}
