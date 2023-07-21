package sollyj.july_3;

import java.util.Arrays;

public class 시소짝꿍 {
	public static void main(String[] args) {
		System.out.println(solution(new int[] {100, 180, 360, 100, 270}));
	}

	private static long solution(int[] weights) {
		long answer = 0;
		int len = weights.length;

		Arrays.sort(weights);

		// for (int i = 0; i < len - 1; i++) {
		// 	int temp1 = weights[i];    // 1:1
		// 	int temp2 = (weights[i] * 3) / 2;    // 2:3
		// 	int temp3 = (weights[i] * 4) / 2;    // 2:4
		// 	int temp4 = (weights[i] * 4) / 3;    // 3:4
		//
		// 	// i + 1부터 temp1,2,3,4중 존재하는지 확인
		// 	// 이분탐색
		// 	if (binarySearch(temp1, weights, i + 1, len - 1)) {
		// 		answer++;
		// 	}
		// 	if (binarySearch(temp2, weights, i + 1, len - 1)) {
		// 		answer++;
		// 	}
		// 	if (binarySearch(temp3, weights, i + 1, len - 1)) {
		// 		answer++;
		// 	}
		// 	if (binarySearch(temp4, weights, i + 1, len - 1)) {
		// 		answer++;
		// 	}
		// }

		int count = 0;

		for (int i = 0; i < len - 1; i++) {
			// 만약 전이랑 같은 무게인 경우 전꺼 count를 그대로 활용할 수 있다.
			// 대신 count - 1 해야함 (이유: 겹치는거)
			// 시간초과 방지
			if (i > 0 && weights[i] == weights[i - 1]) {
				count -= 1;
				answer += count;
				continue;
			}

			count = 0;
			// i + 1 부터 탐색
			for (int j = i + 1; j < len; j++) {
				// 1:1  2:3  2:4  3:4
				if (weights[i] == weights[j] ||
					weights[i] * 3 == weights[j] * 2 ||
					weights[i] * 4 == weights[j] * 2 ||
					weights[i] * 4 == weights[j] * 3) {
					count++;
				}
			}

			answer += count;
		}

		return answer;
	}

	// 이분탐색으로 풀어보려다 실패한...
	// private static boolean binarySearch(int t, int[] weights, int start, int end) {
	// 	while (start <= end) {
	// 		int mid = (start + end) / 2;
	//
	// 		if (t < weights[mid]) {
	// 			end = mid - 1;
	// 		} else if (t > weights[mid]) {
	// 			start = mid + 1;
	// 		} else {
	// 			return true;
	// 		}
	// 	}
	//
	// 	return false;
	// }
}
