package soojik.week6.게임;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) {
    try {
      System.setIn(new FileInputStream("soojik/week6/게임/input.txt"));
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      StringTokenizer st = new StringTokenizer(br.readLine());

      long X = Long.parseLong(st.nextToken());
      long Y = Long.parseLong(st.nextToken());

      // 처음 승률을 Z에 저장한다.
      long Z = Y * 100 / X;

      // 시작점은 0, 끝점은 이제까지 했던 게임 횟수 X 로 둔다.
      long start = 0;
      long end = X;

      // 이미 승률이 99퍼면 더 올라갈 수 없기 때문에 바로 -1을 출력하고 리턴해준다.
      if (Z >= 99) {
        System.out.println(-1);
        return;
      }

      // 이분탐색(upperbound) 으로 처음 현재 승률을 넘어서는 경우를 찾는다.
      while (start < end) {
        long mid = (start + end) / 2;

        // mid 만큼 더 이겼을 때 승률은 win_per 에 넣어준다.
        long win_per = (Y + mid) * 100 / (X + mid);

        // 만약 현재 승률(Z)을 넘어섰다면 mid 만큼 더 이긴 경우를 포함해 다시 이분탐색을 진행하도록 end 가 mid 를 가리키도록 한다.
        if (win_per > Z) {
          end = mid;
        }
        // 만약 현재 승률(Z)보다 작거나 같다면 mid 만큼 더 이긴 경우를 빼고, mid+1 부터 다시 탐색한다.
        else {
          start = mid + 1;
        }
      }

      // 이분탐색이 끝나면 start 또는 end 값을 출력해준다.
      System.out.println(end);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }
}
