package soojik.week12.친구;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("soojik/week12/친구/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    boolean[][] friends = new boolean[N][N];

    // 주어진 친구 관계(YN) 를 friends[][] 에 체크
    for (int i = 0; i < N; i++) {
      String line = br.readLine();

      for (int j = 0; j < N; j++) {
        friends[i][j] = line.charAt(j) == 'Y';
      }
    }

    // 0~n-1 번을 돌며 2-친구 수 세기
    int max = 0;
    for (int i = 0; i < N; i++) {
      // check 에 i와 2-친구인 번호 체크
      boolean[] check = new boolean[N];
      for (int j = 0; j < N; j++) {
        // i와 j가 친구관계면 check[j] 체크
        if (friends[i][j]) {
          check[j] = true;
          // 이어서 j와 친구인 사람도 모두 check 배열에 체크
          for (int k = 0; k < N; k++) {
            if (friends[j][k]) {
              check[k] = true;
            }
          }
        }
      }

      // 체크된 친구 수 세기
      int cnt = 0;
      for (boolean c : check) {
        if (c) ++cnt;
      }

      // 위에서 자기 자신과 친구인 관계를 모두 검사하기 때문에 check 배열에는 자신도 포함되어 있다. 그래서 1을 빼준다.
      max = Math.max(max, cnt - 1);
    }

    System.out.println(max);
  }
}