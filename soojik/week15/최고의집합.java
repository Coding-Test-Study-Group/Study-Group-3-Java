package soojik.week15;

import java.util.Arrays;

public class 최고의집합 {

  public static void main(String[] args) {
    System.out.println(Arrays.toString(solution(2, 8)));
    System.out.println(Arrays.toString(solution(2, 1)));
    System.out.println(Arrays.toString(solution(2, 8)));
  }

  static int[] solution(int n, int s) {
    // 만약 n이 s보다 커서 자연수로 이루어진 n 크기의 집합을 만들 수 없으면 {-1}을 반환해준다.
    if (n > s) return new int[]{-1};

    int[] answer = new int[n];

    // 곱이 최대가 되려면 수들이 공평하게 최대로 커야하므로 n만큼의 몫을 먼저 나누고, n개의 원소 중 나머지만큼에 각 1씩 더해준다.

    int 몫 = s/n;
    int 나머지 = s%n;

    Arrays.fill(answer, 몫);

    for (int i=n-1;i>=n-나머지;i--) {
      answer[i] += 1;
    }

    return answer;
  }
}
