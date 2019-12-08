// 인터페이스 구현 
package ch18.g2;

public class FilmPrinter implements Printer {
  // Printer 규칙에 따라 기능을 구현한다.
  @Override
  public void print(String text) {
    System.out.println("FilmPrinter>> " + text);
  }
}
