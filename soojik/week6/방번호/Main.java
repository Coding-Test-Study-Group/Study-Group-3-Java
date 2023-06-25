package soojik.week6.방번호;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

  public static void main(String[] args) {
    try {
      System.setIn(new FileInputStream("soojik/week6/방번호/input.txt"));
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      /*
      각 자리 숫자를 카운트해, 가장 큰 카운트 수가 최소한으로 필요한 숫자세트 개수가 될 것이다.
      유의할 점은 6과 9는 혼용할 수 있다는 점이다.
      그래서 6과 9는 6으로 모두 카운트 했다가, 카운트가 끝나면 따로 2를 나눠줘 최소한으로 필요한 숫자 세트 수를 업데이트해줬다.
       */

      int N = Integer.parseInt(br.readLine());

      // 각 자리 숫자를 카운트해줄 HashMap 정의
      HashMap<Integer, Integer> map = new HashMap<>();

      // N을 한자리씩 검사
      while (N > 0) {
        // 맨오른쪽부터 검사
        int num = N % 10;

        // 6과 9는 같이 쓸 수 있다고 했으니까 9 개수도 6과 함께 카운트 해줬다.
        if (num == 9) {
          num = 6;
        }

        // key 를 탐색해 map 에 있다면 개수에 1을 더해주고, 아니면 새로 넣어준다.
        if (map.containsKey(num)) {
          map.put(num, map.get(num) + 1);
        }
        else {
          map.put(num, 1);
        }

        // 다음 자리 검사를 위해 오른쪽 자리 버리기
        N /= 10;
      }

      // 6에 9 개수까지 더했으니까 카운트된 개수의 반만 있으면 된다.
      // 예를 들어 6+9 개수가 8개라면 숫자 세트가 최소 4개, 5개라면 최소 3개가 필요하다.
      // 위와 같이 개수를 다시 계산해준 후, map 에 넣어준다.
      if (map.containsKey(6)) map.put(6, map.get(6) % 2 == 0 ? map.get(6) / 2 : map.get(6) / 2 + 1);

      int max = 0;

      // map 의 key 를 돌며 max 값을 찾아낸다.
      for (int i : map.keySet()) {
        max = Math.max(max, map.get(i));
      }

      System.out.println(max);
    } catch (IOException e) {
      System.out.println(e.getLocalizedMessage());
    }

  }
}
