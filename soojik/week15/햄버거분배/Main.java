package soojik.week15.햄버거분배;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
  // 주어진 데이터 N, K, 식탁 정보
  static int N, K;
  static String table;
  // 사람과 햄버거 인덱스만 모아놓은 Queue<Integer> persons, hamburgers
  static Queue<Integer> persons = new LinkedList<>();
  static Queue<Integer> hamburgers = new LinkedList<>();
  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("soojik/week15/햄버거분배/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    table = br.readLine();

    for (int i = 0; i < N; i++) {
      if (table.charAt(i) == 'H') {
        hamburgers.add(i);
        continue;
      }
      persons.add(i);
    }

    int answer = 0;

    int p, h;
    while (!persons.isEmpty()) {
      // persons 에서 하나씩 꺼내서
      p = persons.poll();

      // hamburgers 하나씩 비교
      while (!hamburgers.isEmpty()) {
        // 만약 가장 앞 값이 현재 사람이 뒤쪽으로 닿을 수 없는 위치라면 탐색 중단
        // 앞쪽으로 닿을 수 없는 거리의 햄버거라면 이미 앞쪽의 탐색에서 버려졌을 것
        if (p + K < hamburgers.peek()) {
          break;
        }

        h = hamburgers.poll();

        // 그 외에 닿을 수 있는 거리면 answer 1 증가시키고 탐색 중단
        if (p - K <= h && h <= p + K) {
          ++answer;
          break;
        }
      }
    }

    System.out.println(answer);
  }
}
