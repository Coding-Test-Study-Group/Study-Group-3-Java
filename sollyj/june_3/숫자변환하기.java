// Programmers_숫자변환하기
package sollyj.june_3;

public class 숫자변환하기 {
	static final int MAX = Integer.MAX_VALUE;    // 연산 불가능 함을 의미하는 MAX변수

	public static void main(String[] args) {
		System.out.println(solution(10, 40, 5));
		System.out.println(solution(10, 40, 30));
		System.out.println(solution(2, 5, 4));
	}

	private static int solution(int x, int y, int n) {
		// 1. 테이블 정의
		// 배열에는 [x]에서 [인덱스]가 되기 위한 최소 연산 횟수가 들어감
		// 최종적으로 dp[y]가 정답이 됨
		int[] dp = new int[y + 1];

		// 2. 초기값 세팅
		dp[x] = 0;    // x자신은 연산 횟수 0

		// 3. 점화식
		for (int i = x + 1; i <= y; i++) {
			int temp1 = 0, temp2 = 0, temp3 = 0, d;

			temp1 = (i / 2 >= x && i % 2 == 0) ? dp[i / 2] : MAX;
			temp2 = (i / 3 >= x && i % 3 == 0) ? dp[i / 3] : MAX;
			temp3 = (i - n >= x) ? dp[i - n] : MAX;

			d = Math.min(temp1, temp2);
			d = Math.min(d, temp3);

			// 임시변수에 들어있는 값이 MAX보다 크거나 같다는 것은 i에서 x로 연산 불가능 하단 뜻이므로 MAX를 넣어준다.
			dp[i] = (d < MAX) ? d + 1 : MAX;
		}

		return dp[y] < MAX ? dp[y] : -1;
	}
}
