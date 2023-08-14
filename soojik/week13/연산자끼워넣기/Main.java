package soojik.week13.연산자끼워넣기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static int[] opers = new int[4];
  static int[] nums;
  static long max = Integer.MIN_VALUE;
  static long min = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("soojik/week13/연산자끼워넣기/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());

    nums = new int[N];

    // 문제에 정렬하지 말라고 했다.. 임의로 정렬하면 안된다.
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      nums[i] = Integer.parseInt(st.nextToken());
    }

    // + - * /
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < 4; i++) {
      opers[i] = Integer.parseInt(st.nextToken());
    }

    // sum의 초기값은 nums[0]
    dfs(nums[0], 1);

    System.out.println(max);
    System.out.println(min);
  }

  // N - 1 만큼의 연산자, 남은 숫자를 모두 sum 에 계산해 모든 숫자를 거치면 max, min 업데이트
  // sum : num_idx 까지 계산한 결과
  // num_idx: 계산할 숫자 인덱스
  static void dfs(long sum, int num_idx) {
    // 탐색 끝났으면
    if (num_idx == N) {
      // max, min 업데이트
      max = Math.max(max, sum);
      min = Math.min(min, sum);
      return;
    }

    // 연산자 4개를 돌아가며 모든 경우에 대해 탐색
    for (int i = 0; i < 4; i++) {
      // 해당 인덱스의 연산자가 남아있지 않으면 다음 연산자 탐색 진행
      // 어차피 총 합은 N-1 이니까 탐색 종료 분기문에서 걸릴것이다.
      if (opers[i] == 0) continue;

      // 계산할 연산자 개수 -1
      --opers[i];
      // 연산자 차례는 +, -, *, /
      switch (i) {
        // sum 에 현재 계산할 수(nums[num_idx]) 를 각 연산자대로 계산해 넘겨주고, num_idx 는 다음 수 가리키도록 +1
        case 0:
          dfs(sum + nums[num_idx], num_idx + 1);
          break;
        case 1:
          dfs(sum - nums[num_idx], num_idx + 1);
          break;
        case 2:
          dfs(sum * nums[num_idx], num_idx + 1);
          break;
        case 3:
          // 나눗셈은 정수 몫만 있으면 되니까 따로 실수로 안바꿔줬다.
          dfs(sum / nums[num_idx], num_idx + 1);
          break;
      }
      // 다음 탐색을 위해 연산자 개수 복구
      ++opers[i];
    }
  }
}
