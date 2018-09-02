// 변수 - 메모리에 값 저장
package com.eomcs.basic.ex03;

public class Exam03_3 {
    public static void main(String[] args) {
        int a;
        // 할당(assignment; 배정) 연산자(연산을 수행하는 명령)
        // - 오른쪽에 지정된 값을 왼쪽에 가리키는 이름의 메모리에 
        //   저장을 시키는 명령어.
        // 
        a = 100; // 왼쪽에 a라는 이름을 가진 메모리에 
                 // 100이라는 값을 저장한다.

        int b;
        b = a; // 왼쪽에 b라는 이름의 메모리에 
               // a라는 이름의 메모리에 들어있는 값을 저장한다.

        System.out.println(a); // a라는 메모리의 값을 println()이 출력한다.
        System.out.println(b);
    }
}

// 변수에 값 할당(assignment)
// - 메모리에 값을 저장하는 것을 말한다.
// 문법
// - 변수명 = 변수 또는 리터럴;
// 용어 
// - '=' 왼쪽에 있는 변수를 'l-value'라 부른다.
// - '=' 오른쪽에 있는 변수나 리터럴을 'r-value'라 부른다.
// 