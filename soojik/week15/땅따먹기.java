package soojik.week15;

public class 땅따먹기 {
  public static void main(String[] args) {
    System.out.println(solution(new int[][]{{1,2,3,5},{5,6,7,8},{4,3,2,1}}));
  }
  static int solution(int[][] land) {
    int answer = 0;

    // N행
    int N = land.length;

    // 각 위치에 올 수 있는 최고점을 찾기위해 land와 같은 크기의 이차원 배열
    int[][] dp = new int[N][4];

    // 첫번째 행은 land와 같은 값을 가진다.
    for (int i=0;i<4;i++) {
      dp[0][i] = land[0][i];
    }

    /*
    dp 배열의 각 행은 4개의 값을 갖고있으며, 이전 행의 자신과 같은 위치(열)의 누적 값을 빼고 모두 비교한다.
    얻은 최고값에 현재 땅의 점수를 더해준다.
    */
    for (int i=1;i<N;i++) {
      dp[i][0] = Math.max(dp[i-1][1], Math.max(dp[i-1][2], dp[i-1][3])) + land[i][0];
      dp[i][1] = Math.max(dp[i-1][0], Math.max(dp[i-1][2], dp[i-1][3])) + land[i][1];
      dp[i][2] = Math.max(dp[i-1][0], Math.max(dp[i-1][1], dp[i-1][3])) + land[i][2];
      dp[i][3] = Math.max(dp[i-1][0], Math.max(dp[i-1][1], dp[i-1][2])) + land[i][3];
    }

    // 마지막 행에서 최고 값을 가려낸다.
    for (int i=0;i<4;i++) {
      answer = Math.max(dp[N-1][i], answer);
    }

    return answer;
  }
}
