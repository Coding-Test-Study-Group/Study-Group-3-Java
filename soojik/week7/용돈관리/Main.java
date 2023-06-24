package soojik.week7.용돈관리;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static int N, M;
  static int[] pays;

  public static void main(String[] args) {
    /*
    주어진 N 일동안 M 번만큼, 매번 K 원만큼 인출할 때 K 의 최솟값을 구하는 문제이다.
    N 은 최대 100000이고, N 일만큼 날마다 주어지는 지출은 각 10000이 최대이다.

    범위 안에서 최솟값을 구한다는 부분에서 이분탐색을 떠올릴 수 있었다.
     */
    try {
      System.setIn(new FileInputStream("BOJ/src/P6236/input.txt"));
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      // 이분탐색에 사용될 start, end 변수
      int start = 0;
      int end = 10000 * 100000;

      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());

      // 가장 작은 답 후보(start)는 N 일 중 가장 큰 지출이 된다.
      pays = new int[N];
      for (int i = 0; i < N; i++) {
        pays[i] = Integer.parseInt(br.readLine());
        start = Math.max(start, pays[i]);
      }

      // 이분탐색 (upperbound)
      while (start < end) {
        int mid = (start + end) / 2;

        // 원하는 인출 수(M)보다 작거나 같을 수 있다면
        if (isPossible(mid)) {
          // 목적은 K의 최솟값을 찾는것이니까 현재보다 작은 답이 있는지 정답여부에 mid 를 포함한 채로 탐색 범위 갱신
          end = mid;
        }
        // 원하는 인출 수(M)보다 크다면 조건에 만족하지 않는다는 의미
        else {
          // mid 는 정답 후보에서 뺀채로 탐색 범위 갱신해준다.
          start = mid + 1;
        }
      }

      System.out.println(start);

    } catch (IOException e) {
      System.out.println(e.getLocalizedMessage());
    }
  }

  // 주어진 K 원을 한번에 인출할 금액으로 잡았을 때 조건을 만족하는지 판단하는 메서드
  static boolean isPossible(int k) {
    // result: 몇번 인출했는지
    int result = 0;

    // cnt: 현재 내가 갖고있는 돈
    // pays 배열을 순회하며 현재 갖고있는 돈(cnt)이 지출해야할 돈(p)보다 작다면 새로 인출해야하므로
    int cnt = k;
    for (int p : pays) {
      if (cnt < p) {
        // result 에 1 증가시켜주고
        ++result;
        // 모자라게 되면 남은 금액은 통장에 집어넣고 다시 K 원을 인출하므로,
        // cnt 는 새로 k 로 갱신해준다.
        cnt = k;
      }

      // 마지막으로 오늘 지출해야할 돈을 cnt 에서 빼준다.
      cnt -= p;
    }

    // 문제에서 M 번을 맞추기 위해 일부러 더 인출할 수 있다고 했으므로 반환값은 M 보다 작거나 같은 경우는 모두 조건을 만족한다고 했다.
    return ++result <= M;
  }
}
