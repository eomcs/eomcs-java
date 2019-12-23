// 캡슐화(encapsulation) - 셋터와 겟터 
package com.eomcs.oop.ex07.a;

public class Exam0130 {
    
    public static void main(String[] args) {
        // 환자 데이터를 등록해보자!
        Customer3 c1 = new Customer3();
        
        // 인스턴스 변수에 직접 접근할 수 없기 때문에 메서드를 통해 값을 넣어야 한다.
        // => Customer3에는 인스턴스 변수의 값을 설정할 수 있는 셋터가 구비되어 있다.
        c1.setName("홍길동");
        c1.setAge(300);
        c1.setWeight(100);
        c1.setHeight(-50);
        // 셋터 메서드에서 유효한 값이 아니면 필터링하여 처리할 것이다.

        // 값을 꺼내보자!
        // => 인스턴스 변수에 직접 접근할 수 없기 때문에 메서드를 통해 값을 꺼내야 한다. 
        // => Customer3에는 인스턴스 변수의 값을 리턴해주는 겟터가 구비되어 있다.
        System.out.printf("%s, %d, %d, %d\n", 
                c1.getName(), c1.getAge(), c1.getWeight(), c1.getHeight());
    }
}







