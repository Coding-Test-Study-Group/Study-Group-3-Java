package sollyj.july_1;

public class 등굣길 {
	public static void main(String[] args) {
		System.out.println(solution(4, 3, new int[][] {{2, 2}}));
		System.out.println(solution(4, 4, new int[][] {{3, 2}, {2, 4}}));
		System.out.println(solution(5, 3, new int[][] {{4, 2}}));
		System.out.println(solution(2, 2, new int[][] {{2, 1}, {1, 2}}));
	}

	private static int solution(int m, int n, int[][] puddles) {
		int answer = 0;

		// 1. 테이블 정의
		// dp[x][y]에 들어갈 값: (x, y)까지의 최단 경로 개수
		int[][] dp = new int[m + 1][n + 1];

		// 2. 초기값
		// 물에 잠긴 지역은 못가므로 -1로 초기화
		for (int[] puddle : puddles) {
			dp[puddle[0]][puddle[1]] = -1;
		}
		// dp를 하기 위해 (1, 1)에 1을 넣어준다.
		dp[1][1] = 1;

		// 3. 점화식
		for (int x = 1; x <= m; x++) {
			for (int y = 1; y <= n; y++) {
				if (dp[x][y] == -1) {
					dp[x][y] = 0;    // 물 웅덩이면 0으로 바꾸고 continue, 이유는 dp 계산하기 위하여
					continue;
				}

				if (x - 1 >= 1) {
					dp[x][y] += dp[x - 1][y] % 1000000007;    // dp배열에도 % 1000000007 해야 효율성 테스트를 통과한다. 이유는...?
				}

				if (y - 1 >= 1) {
					dp[x][y] += dp[x][y - 1] % 1000000007;
				}
			}
		}

		// 4. 정답 도출
		answer = dp[m][n] % 1000000007;

		return answer;
	}
}
