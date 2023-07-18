package sollyj.july_3;

public class 기지국설치 {
	public static void main(String[] args) {
		System.out.println(solution(11, new int[] {4, 11}, 1));
		System.out.println(solution(16, new int[] {9}, 2));
	}

	private static int solution(int n, int[] stations, int w) {
		int answer = 0;

		int start = 1;    // 첫 시작 인덱스는 1
		int end;

		// 기지국이 설치되어있는 아파트를 순회
		// 해당 아파트 기준으로 왼쪽을 볼 것
		for (int s : stations) {
			// 아파트 기준으로 왼쪽에 전파가 안퍼지는 구역을 찾을 것이다.
			// 만약 start까지 전파가 퍼진다면 다음 아파트로 넘어가도된다.
			if (s - w - 1 >= start) {
				end = s - w - 1;

				int gap = end - start + 1;    // 간격

				int minCnt = gap / (w * 2 + 1);    // 그 간격 내에서 필요한 최소 기지국 개수
				if (gap % (w * 2 + 1) != 0)    // 나머지가 나오면 +1
					minCnt++;

				answer += minCnt;
			}

			start = s + w + 1;    // start를 갱신해주고 다음 반복문 실행
		}

		// 왼쪽만 봤으니까 오른쪽이 남아있다면 다음 반복문 실행
		if (stations[stations.length - 1] + w + 1 <= n) {
			end = n;

			int gap = end - start + 1;

			int minCnt = gap / (w * 2 + 1);
			if (gap % (w * 2 + 1) != 0)
				minCnt++;

			answer += minCnt;
		}

		return answer;
	}
}
