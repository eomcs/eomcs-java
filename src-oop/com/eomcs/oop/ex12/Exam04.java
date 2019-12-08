// 람다(lambda) - 파라미터와 리턴 값이 있는 람다
package com.eomcs.oop.ex12;

public class Exam04 {

    static interface Calculator {
        int compute(int a, int b);
    }
    
    static void test(Calculator c) {
        System.out.println(c.compute(100, 200));
    }
    
    public static void main(String[] args) {
        
        // 파라미터와 리턴 값이 있는 메서드 구현하기
        test((a, b) -> a + b);
        
        // 위의 람다 표현식은 다음 익명 클래스와 같은 의미다. 
        test(new Calculator() {
            public int compute(int a, int b) {
                return a + b;
            }
        });
        
        // 여러 문장을 실행하는 경우 블록 {}으로 감싸라!
        test((a, b) -> {
            int sum = 0;
            for (int i = a; i <= b; i++) {
                sum += i;
            }
            return sum;
        });
        // 위의 람다 표현식은 다음 익명 클래스와 같은 의미다.
        test(new Calculator() {
            public int compute(int a, int b) {
                int sum = 0;
                for (int i = a; i <= b; i++) {
                    sum += i;
                }
                return sum;
            }
        });
        
    }

}







