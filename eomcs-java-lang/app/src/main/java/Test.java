public class Test {

  public static void main(String[] args) {
    int x = 100;

    test(x++);

    int temp = x;
    x = x + 1;
    test(temp);
  }

  static void test(int a) {
    System.out.println(a);
  }

}
