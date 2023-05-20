// Baekjoon_14501_퇴사
package sollyj.may_4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 퇴사 {
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			StringTokenizer st;

			int N = Integer.parseInt(br.readLine());
			int[] T = new int[N];    // 상담 기간
			int[] P = new int[N];    // 금액

			// 1. 테이블 정의
			// n일까지의 최대 수익
			int[] profit_max = new int[N + 1];

			// 2. 초기값 세팅
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				T[i] = Integer.parseInt(st.nextToken());
				P[i] = Integer.parseInt(st.nextToken());
			}

			// 3. 점화식
			for (int i = 0; i < N; i++) {
				if (i + T[i] <= N) {    // i일에 상담을 했을때 퇴사일 까지 상담을 마칠수 있다면
					// profit_max[i + T[i]]: 현재 값
					// profit_max[i] + P[i]: i일에 상담을 했을 때 수익
					profit_max[i + T[i]] = Math.max(profit_max[i + T[i]], profit_max[i] + P[i]);
				}

				// 예외 (예제 입력4 같은 경우)
				// 꼭 i + T[i]만 상담 할 수 있는 것은 아니고, 상담 다음날 상담을 할 수도 있는 것이다.
				profit_max[i + 1] = Math.max(profit_max[i], profit_max[i + 1]);
			}

			// 4. 답 도출
			System.out.println(profit_max[N]);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
