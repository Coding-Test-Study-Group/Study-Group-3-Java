package sollyj.week1;

import java.util.ArrayList;
import java.util.Arrays;

public class 정렬된_리스트_병합 {
	public static void main(String[] args) {
		int[][] input1 = {{1, 4, 5}, {1, 3, 4}, {2, 6}};
		int[][] input2 = {{1, 4}, {1, 3, 4, 7, 8}, {6}, {7, 7}};
		System.out.println(Arrays.toString(solution(input2)));
	}

	private static int[] solution(int[][] lists) {
		int[] answer = {};

		for (int[] list : lists) {
			// list의 값들을 answer에 넣기
			for (int i = 0; i < list.length; i++) {
				// 배열에 값을 추가할땐 배열 복사 후 넣는다.
				answer = Arrays.copyOf(answer, answer.length + 1);
				answer[answer.length - 1] = list[i];
			}
		}

		Arrays.sort(answer);

		return answer;
	}
}
