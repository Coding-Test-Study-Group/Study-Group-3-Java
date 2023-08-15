package sollyj.aug_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class n2배열자르기 {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(3, 2, 5)));
		System.out.println(Arrays.toString(solution(4, 7, 14)));
	}

	private static int[] solution(int n, long left, long right) {
		List<Long> answer_list = new ArrayList<>();

		for (long i = left; i <= right; i++) {
			answer_list.add(Math.max(i / n, i % n) + 1);
		}

		return answer_list.stream().mapToInt(Long::intValue).toArray();
	}
}
