// Object 클래스의 메서드를 오버라이딩 하기 - toString()
package com.eomcs.oop.ex06.d;

public class Exam0110 {

    static class Score {
        String name;
        int kor;
        int eng;
        int math;
        int sum;
        float aver;
        
        public Score(String name, int kor, int eng, int math) {
            this.name = name;
            this.kor = kor;
            this.eng = eng;
            this.math = math;
            this.sum = kor + eng + math;
            this.aver = this.sum / 3f;
        }
    }
    
    public static void main(String[] args) {
        Score s1 = new Score("홍길동", 100, 100, 100);
        System.out.println(s1.toString());
        
        // Object로부터 상속 받아 사용하는 toString()은 다음 형식의 문자열을 리턴한다. 
        // "패키지 이름을 포함한 클래스명@인스턴스 식별자"
        // 예) step11.ex12.Exam01@12a74311
        // 인스턴스 식별자?
        // => 인스턴스의 주소가 아니다. 자바는 절대로 메모리 주소를 알려주지 않는다!
        // => 단지 인스턴스를 식별할 때 사용하라고 JVM이 임의로 붙인 식별자이다.
        // => 이 식별자를 "해시값(hashcode)"라 부른다.
        // 
    }
}






