package soojik.week13.예산;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int[] requests;
  /*
  모든 요청을 들어줄 수 없는 경우만 이분탐색을 이용해 상한액을 정했다.
   */
  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("soojik/week13/예산/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    requests = new int[N];

    // 이분탐색에서 최대값에 이용할 max 값
    int max = 0;
    // 모든 요청을 들어줄 수 있는지 확인
    int sum = 0;
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      requests[i] = Integer.parseInt(st.nextToken());
      sum += requests[i];
      max = Math.max(max, requests[i]);
    }

    // 이분탐색을 위해 정렬
    Arrays.sort(requests);

    // 지원할 수 있는 총액
    int M = Integer.parseInt(br.readLine());

    // 요청을 들어줄 수 있다면 바로 max 출력
    if (sum <= M) {
      System.out.println(max);
      return;
    }

    // 이분탐색은 최저 값은(M은 N 이상) 1, 최대 값은 나온 요청 중 가장 큰 값으로 정했다. -> 이분탐색이 진행되는 경우는 모든 요청을 들어줄 수 없을 때만 이니까
    int start = 1;
    int end = max;
    int mid = (start + end) / 2;

    while (start <= end) {

      // 임의의 상한액
      mid = (start + end) / 2;

      // 만약 처리할 수 있다면
      if (isPossible(M, mid)) {
        // start 보다 더 높은 값으로 찾도록
        start = mid + 1;
      }
      else {
        // 들어줄 수 없다면 현재 상한액(mid)는 넣지 않고 상한액을 낮춘다.
        end = mid - 1;
      }
    }

    // 이진탐색이 끝나면 start 는 end 를 하나 넘어선 상태니까 최대 상한액에서 1만큼 큰 값이다. (end 는 안되는 값 중 가장 낮은 값을 가리키고 있을 것이다.)
    System.out.println(start - 1);
  }

  // 주어진 상한액(upperLimit)으로 모든 요청을 처리할 수 있는지 확인하는 메서드
  static boolean isPossible(int M, int upperLimit) {
    for (int req : requests) {
      if (req > upperLimit) {
        M -= upperLimit;
        continue;
      }
      M -= req;
    }

    return M >= 0;
  }
}
