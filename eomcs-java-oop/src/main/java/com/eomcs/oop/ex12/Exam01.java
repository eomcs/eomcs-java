// 람다(lambda) - 익명 클래스
package com.eomcs.oop.ex12;

public class Exam01 {

    static interface Player {
        void play();
    }
    
    static void testPlayer(Player player) {
        player.play();
    }
    
    public static void main(String[] args) {
        // 메서드가 한 개 짜리인 인터페이스를 익명 클래스 문법을 이용하여 구현하기
        Player player = new Player() {
            @Override
            public void play() {
                System.out.println("실행~~~~");
            }
        }; 
        
        testPlayer(player);

    }

}







