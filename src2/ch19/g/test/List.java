package ch19.g.test;

import java.util.Arrays;

public class List {
  int[] values;
  
  public List(int[] arr) {
    values = arr;
  }
  
  public int[] toArray(Filter filter) {
    int[] temp = new int[values.length];
    int count = 0;
    
    for (int i = 0; i < values.length; i++) {
      if (filter.accept(values[i])) {
        temp[count++] = values[i];
      }
    }
    return Arrays.copyOf(temp, count);
  }
}
