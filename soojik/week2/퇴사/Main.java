package soojik.week2.퇴사;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("soojik/week2/퇴사/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    int[] T = new int[N + 1];
    int[] P = new int[N + 1];

    StringTokenizer st;
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());

      T[i] = Integer.parseInt(st.nextToken());
      P[i] = Integer.parseInt(st.nextToken());
    }

    /*
    1 <= N <= 15
    1 <= T[i] <= 5
    1 <= P[i] <= 1000

    마지막 날짜의 최댓값은 N + T[i] 로 20일 이니까 넉넉하게 30 으로 설정

    dp[i]: i 일 직전까지 얻을 수 있는 최대 비용
     */
    int[] dp = new int[30];
    // 1번 인덱스부터 순회하기 위해 가장 첫번째 일을 했을 때 얻을 수 있는 비용 먼저 계산
    dp[T[0]] = P[0];

    // 1번 인덱스부터 순회
    for (int i = 1; i <= N; i++) {
      // 전날까지 얻을 수 있는 최대 비용과 오늘까지의 최대 비용을 비교해 계속해서 최댓값으로 업데이트해준다.
      dp[i] = Math.max(dp[i - 1], dp[i]);
      // 끝나는 날(i+T[i]) 에 누적된 최대 비용을 업데이트 해준다.
      // dp[i] + P[i] -> 오늘(i) 일 했을 때 얻을 수 있는 비용(P[i])을 오늘의 누적 값(dp[i]) 에서 더해준다.
      // dp[i + T[i]] -> 이미 끝나는 날(i + T[i])의 누적 최대 값이 크다면 업데이트 하지 않는다.
      dp[i + T[i]] = Math.max(dp[i] + P[i], dp[i + T[i]]);
    }

    // N+1 일 직전까지 얻을 수 있는 최대 비용을 출력한다.
    System.out.println(dp[N]);
  }
}
