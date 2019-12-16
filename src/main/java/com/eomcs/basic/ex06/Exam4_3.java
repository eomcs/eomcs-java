package com.eomcs.basic.ex06;

//# 흐름 제어문 - for 반복문과 배열
//
public class Exam4_3 {
  public static void main(String[] args) {
    /*
        String[] names = new String[5];
        names[0] = "홍길동";
        names[1] = "임꺽정";
        names[2] = "유관순";
        names[3] = "윤봉길";
        names[4] = "안중근";

        String[] names = 
            new String[] {"홍길동", "임꺽정", "유관순", "윤봉길", "안중근"};
     */

    String[] names = {"홍길동", "임꺽정", "유관순", "윤봉길", "안중근"};

    for (int i = 0; i < names.length; i++) 
      System.out.println(names[i]);

    System.out.println("----------------------");

    // 배열의 처음부터 끝까지 값을 꺼내는 것이라면 
    // 다음의 for 문법을 사용하라! 아주 편하다!
    // for (값을 저장할 변수 선언 : 배열주소) 문장;
    for (String name : names) 
      System.out.println(name);

    // 위의 방식으로 for 문을 사용할 경우
    // 변수 선언을 바깥에 둘 수 없다.
    /*
        String name2;
        for (name2 : names) // 컴파일 오류!
            System.out.println(name2);
     */
  }
}
