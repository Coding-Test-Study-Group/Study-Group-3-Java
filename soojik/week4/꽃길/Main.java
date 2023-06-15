package soojik.week4.꽃길;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static int[][] G;
  static boolean[][] visit;

  static int[] dx = {-1, 0, 1, 0};
  static int[] dy = {0, -1, 0, 1};

  static int answer = Integer.MAX_VALUE;

  public static void main(String[] args) {

    try {
      System.setIn(new FileInputStream("soojik/week4/꽃길/input.txt"));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    try {
      N = Integer.parseInt(br.readLine());

      G = new int[N][N];

      StringTokenizer st;
      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          G[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      visit = new boolean[N][N];

      min_sum(0, 0);

      System.out.println(answer);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  static void min_sum(int seed, int sum) {

    // 씨앗 3개 다 심었으면 지금까지 구했던 최솟값과 비교해 작으면 answer 새로 업데이트
    if (seed == 3) {
      answer = Math.min(answer, sum);
      return;
    }

    // 현위치 기준으로 상하좌우 총 5평이 모두 유효한 땅이어야하기 때문에 씨앗 심는 위치는 1 ~ N-2
    for (int i = 1; i < N - 1; i++) {
      for (int j = 1; j < N - 1; j++) {
        // 만약 현재 위치 방문한적 없고, 주변도 깨끗하다면
        if (valid(i, j)) {
          // 다음 씨앗 심을 자리 구하기 위해 min_sum 재귀호출
          // get_sum 메서드로 현재 및 주변 땅값을 sum 과 합산해 넘겨준다.
          min_sum(seed + 1, sum + get_sum(i, j));

          // 재귀호출 끝나면 다음 위치 탐색을 위해 현재 및 주변 위치 visit 배열을 초기화해준다.
          clear_visit(i, j);
        }
      }
    }
  }

  // 현재와 주변 땅 모두 유효한지 체크할 valid 메서드 정의
  static boolean valid(int x, int y) {
    if (visit[x][y]) return false;

    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];

      if (visit[nx][ny]) return false;
    }

    return true;
  }

  // 현재와 주변 땅값 합산해 반환한다.
  static int get_sum(int x, int y) {
    visit[x][y] = true;

    int sum = G[x][y];
    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];

      visit[nx][ny] = true;

      sum += G[nx][ny];
    }

    return sum;
  }

  // dfs 로 탐색 끝낸 후에는 주변 visit 모두 false 로 정리
  static void clear_visit(int x, int y) {
    visit[x][y] = false;

    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];

      visit[nx][ny] = false;
    }
  }
}
