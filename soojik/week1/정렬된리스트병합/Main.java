package soojik.week1.정렬된리스트병합;

import java.util.*;

public class Main {
  public static void main(String[] args) {
    int[][] input_arr =
            {
                    {1, 4, 5},
                    {1, 3, 4},
                    {2, 6}
            };

    List<Integer> list = new ArrayList<>();

    for (int[] ints : input_arr) {
      for (int i : ints) {
        list.add(i);
      }
    }

    list.sort(Comparator.naturalOrder());

    System.out.println(list);
  }
}
