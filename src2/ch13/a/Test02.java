// 기능 확장하기 - Score 클래스에 음악과 미술 과목을 추가하기
package ch13.a;

public class Test02 {
  public static void main(String[] args) {
    Score2 s = new Score2();
    s.setName("홍길동");
    s.setKor(100);
    s.setEng(100);
    s.setMath(100);
    s.setMusic(100);
    s.setArt(100);
    System.out.printf("%s: %d(%.1f)\n", s.getName(), s.getSum(), s.getAver());
  }
}
