package soojik.week13;

public class 숫자의표현 {

  public static void main(String[] args) {
    System.out.println(solution(15));
  }

  static int solution(int n) {
    int answer = 0;

    // 연속된 자연수의 합이 n과 같아야하니까 누적합 사용
    // n도 답에 포함이 되니까 0부터 n까지 n+1 크기 만큼의 누적합 저장할 dp 배열
    int[] dp = new int[n + 1];

    // dp[i]에는 0부터 i까지의 누적합
    for (int i=1;i<=n;i++) dp[i] = i + dp[i-1];

    // 투포인터로 n은 자연수니까(0 이상) 시작은 0부터 1까지의 합부터 계산해 sum에 저장
    int start = 0;
    int end = 1;
    int sum;

    // start 와 end 가 만날일은 없고, end가 n과 만나면 탐색 끝나도록
    while (end <= n) {
      // start부터 end까지의 합
      sum = dp[end] - dp[start];
      // sum이 n과 같다면
      if (sum == n) {
        // 답 1증가, start랑 end 둘 다 1씩 증가
        ++answer;
        ++start;
        ++end;
      }
      // sum이 n보다
      // 작다면
      else if (sum < n) {
        // sum이 커져야하니까 end 증가
        ++end;
      }
      // 크다면
      else {
        // sum이 작아져야하니까 start 증가
        ++start;
      }
    }

    return answer;
  }
}
