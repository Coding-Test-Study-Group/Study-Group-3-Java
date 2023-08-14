package sollyj.aug_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 연산자끼워넣기 {
	static int[] nums;
	static int[] operators;    // +, -, *, /
	static int max, min;

	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			StringTokenizer st;

			int N = Integer.parseInt(br.readLine());
			nums = new int[N];
			operators = new int[4];
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				operators[i] = Integer.parseInt(st.nextToken());
			}

			dfs(nums[0], 1);

			System.out.println(max);
			System.out.println(min);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	private static void dfs(int nowNum, int nextIdx) {
		// 재귀를 끝낼 조건
		if (nextIdx == nums.length) {
			if (max < nowNum)
				max = nowNum;

			if (min > nowNum)
				min = nowNum;

			return;
		}

		for (int i = 0; i < 4; i++) {
			if (operators[i] > 0) {
				operators[i]--;

				switch (i) {
					case 0:
						dfs(nowNum + nums[nextIdx], nextIdx + 1);
						break;
					case 1:
						dfs(nowNum - nums[nextIdx], nextIdx + 1);
						break;
					case 2:
						dfs(nowNum * nums[nextIdx], nextIdx + 1);
						break;
					case 3:
						dfs(nowNum / nums[nextIdx], nextIdx + 1);
						break;
				}

				operators[i]++;
			}
		}
	}
}
