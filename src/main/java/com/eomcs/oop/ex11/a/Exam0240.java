// non-static nested class = inner class 응용
package com.eomcs.oop.ex11.a;

import java.util.ArrayList;
import java.util.List;

public class Exam0240 {
  public static void main(final String[] args) {
    final Musics m1 = new Musics();
    m1.add("aaa.mp3");
    m1.add("bbb.mp3");
    m1.add("bbb.mp3");

    final Musics.Player p1 = m1.new Player();
    final Musics.Player p2 = m1.new Player();

    p1.play();
    p2.play();

    final Musics m2 = new Musics();
    m2.add("xxx.mp3");
    m2.add("yyy.mp3");

    final Musics.Player p3 = m2.new Player();

    p3.play();
  }
}


class Musics {

  class Player {
    public void play() {
      // 이 메서드가 호출되려면, Player 객체가 존재해야 한다.
      // Player 객체가 존재하려면, Musics 객체가 존재해야 한다.
      // 따라서 이 메서드가 호출될 때는 이미 Musics 객체가 존재한 상태이다.
      // 그 Musics 객체로 Player 객체를 만든 것이다.
      // 따라서 inner class 의 객체는 항상 자신을 만든 객체의 주소를 보관한다.
      // => 바깥_클래스명.this 내장 변수에 보관한다.
      // => Player를 만든 Musics 객체를 사용하고 싶다면 "바깥_클래스명.this" 변수를 이용하라.
      //
      for (final String song : songs) {
        System.out.println(song);
      }
      System.out.println("-----------------------------");
    }
  }

  List<String> songs = new ArrayList<>();

  public void add(final String song) {
    songs.add(song);
  }

  public void delete(final int index) {
    songs.remove(index);
  }
}


