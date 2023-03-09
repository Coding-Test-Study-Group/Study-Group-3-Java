package soojik.week1.그룹애너그램;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    /* 파일로 테스트데이터 입력 받기
    System.setIn(new FileInputStream("soojik/week1/그룹애너그램/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] input_str = new String[6];
    for (int i = 0; i < 6; i++) {
      input_str[i] = br.readLine();
    }
     */

    String[] input_str = {"eat", "tea", "tan", "ate", "nat", "bat"};

    /*
      key: 알파벳 순으로 정렬된 단어
      value: 같은 구성을 가진 단어의 배열
     */
    HashMap<String, List<String>> map = new HashMap<>();

    int len = input_str.length;
    // input 문자열 배열 순회
    for (int i = 0; i < len; i++) {
      // 각 문자열 정렬
      char[] tmp = input_str[i].toCharArray();
      Arrays.sort(tmp);

      // 현재 문자열을 추가할 list_tmp
      // 밑에서 이전에 같은 구성의 알파벳의 유무에 따라 다른 값이 들어간다.
      List<String> list_tmp;

      // 정렬된 문자열 tmp가 map에 있는 경우
      // 같은 구성을 가진 문자열이 1개 이상 있었다는 의미
      // map에서 이제까지 나왔던 같은 구성의 단어를 모은 리스트 가져온다.
      if (map.containsKey(new String(tmp))) {
        list_tmp = map.get(new String(tmp));
      }
      // 정렬된 문자열 tmp가 map에 없는 경우
      // 같은 구성의 문자열이 없었다는 의미
      // list_tmp은 빈 리스트로 정의
      else {
        list_tmp = new ArrayList<>();
      }
      // 앞에서 만들어진 list_tmp에 현재 문자열을 넣는다.
      list_tmp.add(input_str[i]);
      // 완성된 list_tmp를 map에 추가한다.
      map.put(new String(tmp), list_tmp);
    }

    /*
    처음 코드
    int map_size = map.size();

    // 정답 출력할 List 배열
    List[] answer = new ArrayList[map_size];

    int idx = 0;
    // map 돌며 answer 배열에 list 추가해준다.
    for (String key : map.keySet()) {
      answer[idx++] = map.get(key);
    }

    // 답 출력
    System.out.println(Arrays.toString(answer));

     */

    // map.values 는 Collection<List<String>> 형태로 반환되기 때문에
    // 이를 상속받는 배열 형태의 ArrayList 로 바꿔준다.
    ArrayList<List<String>> answer = new ArrayList<>(map.values());

    // 답 출력
    System.out.println(answer);

  }
}