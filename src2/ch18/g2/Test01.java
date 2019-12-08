package ch18.g2;

public class Test01 {

  public static void main(String[] args) {
    PaperPrinter p1 = new PaperPrinter();
    FilmPrinter p2 = new FilmPrinter();
    
    display(p1, "Hello!");
    display(p2, "World!");
    
  }
  
  private static void display(Printer printer, String text) {
    printer.print(text);
  }

}
