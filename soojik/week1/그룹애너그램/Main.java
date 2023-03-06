package soojik.week1.그룹애너그램;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    // 테스트데이터 입력 받기
    System.setIn(new FileInputStream("soojik/week1/그룹애너그램/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] input_str = new String[6];
    for (int i = 0; i < 6; i++) {
      input_str[i] = br.readLine();
    }

    int len = input_str.length;
    HashMap<String, List<String>> map = new HashMap<>();

    // 문자열 정렬 후 sorted_str 배열에 순서 그대로 저장
    for (int i = 0; i < len; i++) {
      char[] tmp = input_str[i].toCharArray();
      Arrays.sort(tmp);
      List<String> list_tmp;

      // map에 있는 경우
      if (map.containsKey(new String(tmp))) {
        list_tmp = map.get(new String(tmp));
      } else {
        list_tmp = new ArrayList<>();
      }
      list_tmp.add(input_str[i]);
      map.put(new String(tmp), list_tmp);
    }

    int map_size = map.size();

    List[] answer = new ArrayList[map_size];

    int idx = 0;
    for (String key : map.keySet()) {
      answer[idx++] = map.get(key);
    }

    System.out.println(Arrays.toString(answer));
  }
}