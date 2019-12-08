package ch19.g.test;

public class EvenFilter implements Filter {

  @Override
  public boolean accept(int value) {
    return (value % 2) == 0;
  }

}
