package sollyj.july_2;

import java.util.Arrays;
import java.util.Comparator;

public class 단속카메라 {
	public static void main(String[] args) {
		System.out.println(solution(new int[][] {{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}}));
		System.out.println(solution(new int[][] {{-10, 0}, {-3, 5}, {-5, 7}, {9, 11}}));
	}

	private static int solution(int[][] routes) {
		int answer = 1;

		// routes[][1] 기준으로 오름차순 정렬
		Arrays.sort(routes, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});

		int min = routes[0][1];    // 첫번째 차량이 고속도로에서 나간 지점을 저장

		for (int[] route : routes) {
			// 현재 차량의 시작 지점이 min 값보다 크다는 것은 겹치지 않는다는 뜻
			if (route[0] > min) {
				min = route[1];    // min 값을 현재 차량의 나간 시점으로 새로 갱신
				answer++;    // 단속카메라 추가
			}
		}

		return answer;
	}
}
