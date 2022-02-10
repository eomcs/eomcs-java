import java.util.ArrayList;

public class Test {

  public static void main(String[] args) {
    ArrayList list = new ArrayList();
    list.add(100);
    list.add(200);
    list.add(400);
    list.add(500);

    for (int i = 0; i < list.size(); i++) {
      System.out.println(list.get(i));
      if (i == 2) {
        list.remove(2);
      }
    }
  }

}
