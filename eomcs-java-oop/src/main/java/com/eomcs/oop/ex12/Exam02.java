// 람다(lambda) - 람다 표현식 사용
package com.eomcs.oop.ex12;

public class Exam02 {

    static interface Player {
        void play();
    }
    
    static void testPlayer(Player player) {
        player.play();
    }
    
    public static void main(String[] args) {
        // 메서드가 한 개 짜리인 인터페이스를 lambda 문법을 이용하여 구현하기
        // => 파라미터가 없고 리턴 값이 없는 메서드를 자동으로 오버라이딩 한다.
        // => 람다 표현식은 메서드 한 개짜리 인터페이스를 구현할 때 사용한다.
        // => 메서드 한 개 짜리 인터페이스를 구현할 때도  
        //    늘 완전한 형식을 갖춰 구현해야 했다.
        // => 하지만 lambda 표현식을 사용하면 훨씬 더 간결하게 작성할 수 있다.
        Player player = () -> System.out.println("실행~~~~");
        
        testPlayer(player);

    }

}







