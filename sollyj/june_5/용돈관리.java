package sollyj.june_5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 용돈관리 {
	static int[] costs;

	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			costs = new int[N];
			int maxCost = Integer.MIN_VALUE;    // N일 동안 중 가장 큰 지출

			for (int i = 0; i < N; i++) {
				costs[i] = Integer.parseInt(br.readLine());
				maxCost = Math.max(maxCost, costs[i]);
			}

			// 이분탐색
			int start = maxCost;
			int end = maxCost * 100_000;
			int answer = 0;

			while (start <= end) {
				int mid = (start + end) / 2;

				int tempM = manageMoney(mid);

				if (tempM > M) {
					// M횟수를 초과하면 k(mid)를 늘려야한다.
					start = mid + 1;
				} else if (tempM <= M) {
					// M횟수 이하면 k(mid)를 줄인다.
					// 이 때 M횟수 이하여도 넣다 뺐다 해서 M번을 채울 수 있기에 answer에도 넣어준다.
					end = mid - 1;
					answer = mid;
				}
			}

			System.out.println(answer);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	// k원을 인출하고자 할 때 N일 동안의 인출 횟수를 반환하는 메서드
	private static int manageMoney(int k) {
		int count = 1;    // 한번 카운트하고 시작
		int nowMoney = k;    // 한번 인출하고 현재 금액

		for (int c : costs) {
			nowMoney -= c;    // 돈 지출

			// 돈이 모자르다면 인출하여 사용
			// ex)
			// k = 500, 첫번째 두번째날 지출금액 400 500이라고 하자.
			// 첫번째날 지출하고 100원이 남았지만, 두번째날에 못쓰므로 100원은 통장에 넣고 다시 인출하여 사용한다는 뜻
			if (nowMoney < 0) {
				++count;
				nowMoney = k - c;    // 돈 인출하여 사용
			}
		}

		return count;
	}
}
