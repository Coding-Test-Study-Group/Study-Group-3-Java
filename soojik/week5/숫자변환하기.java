package soojik.week5;

public class 숫자변환하기 {

  public static void main(String[] args) {

    System.out.println(solution(10, 40, 5));
    System.out.println(solution(10, 40, 30));
    System.out.println(solution(2, 5, 4));
  }


  static int solution(int x, int y, int n) {
    int answer = 0;

    /*
    dp 라는 배열을 만들어 각 i 자리에 x로 부터 얼만큼의 연산을 통해 도달했는지 최솟값을 저장했다.
     */

    // 조건에 x와 y가 같을 수도 있다고 했으니까 먼저 같은지 체크
    if (x==y) return answer;

    // y가 최대 1000000 까지 나올 수 있는데 반복문에서 y-1 까지 순회하며 다음 연산 결과 및 횟수를 저장하기 때문에 *3 해서 3000000 만큼의 크기로 만들어주었다.
    int[] dp = new int[3000000];

    // x 부터 y 까지 순회
    for (int i=x;i<y;i++) {
      // 만약 가장 첫번째 연산이라면 다음 i*3, i*2, i+n 에 도달할 연산 횟수는 1이 된다.
      if (i == x) {
        dp[i*3] = 1;
        dp[i*2] = 1;
        dp[i+n] = 1;
        continue;
      }
      // 첫번째 연산이 아닌데 dp[i] 라면 이전의 연산을 통해 여기 도달한 적 없으므로 이어나갈 필요가 없다.
      if (dp[i] == 0) {
        continue;
      }
      // 위 조건에 모두 걸리지 않은 상태이면 첫번째 연산이 아니면서 도달한적 있는 숫자이므로
      // 다음 연산 결과(i*3, i*2, i+n) 가 만들어질 때까지의 최소 연산 횟수를 업데이트 해주었다.
      // 만약 다음 연산 결과가 0이라면 아직 한번도 도달하지 않은 지역이니까 지금까지 왔던 최소 횟수에 1을 더해주고
      // 아니라면 다음 연산 결과의 최소 연산 횟수와 현재 도달한 수의 최소 횟수에 1을 더한 값과 비교해 더 작은 값으로 업데이트해준다.
      dp[i*3] = dp[i*3] == 0 ? dp[i] + 1 : Math.min(dp[i*3], dp[i] + 1);
      dp[i*2] = dp[i*2] == 0 ? dp[i] + 1 : Math.min(dp[i*2], dp[i] + 1);
      dp[i+n] = dp[i+n] == 0 ? dp[i] + 1 : Math.min(dp[i+n], dp[i] + 1);
    }

    // 만약 y까지 도달하지 못했다면 아무 연산 횟수가 업데이트되지 않았으니까 -1을 반환해준다.
    return dp[y] == 0 ? -1 : dp[y];
  }
}
