package sollyj.june_5;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class 이중우선순위큐 {
	public static void main(String[] args) {
		System.out.println(
			Arrays.toString(solution(new String[] {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"})));

		System.out.println(
			Arrays.toString(
				solution(new String[] {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"})));
	}

	private static int[] solution(String[] operations) {
		int[] answer = {};

		PriorityQueue<Integer> pqLow = new PriorityQueue<>();    // 낮은 숫자 우선순위 큐
		PriorityQueue<Integer> pqHigh = new PriorityQueue<>(Collections.reverseOrder());    // 높은 숫자 우선순위 큐

		for (String operation : operations) {
			String o = operation.split(" ")[0];
			int n = Integer.parseInt(operation.split(" ")[1]);

			if (o.equals("I")) {
				pqLow.add(n);
				pqHigh.add(n);
			} else if (o.equals("D")) {
				if (pqLow.isEmpty() || pqHigh.isEmpty())
					continue;

				if (n < 0) {
					// 최솟값 삭제
					int min = pqLow.poll();
					pqHigh.remove(min);
				} else {
					// 최댓값 삭제
					int max = pqHigh.poll();
					pqLow.remove(max);
				}
			}
		}

		if (pqLow.isEmpty() || pqHigh.isEmpty()) {    // 둘중에 하나만 비어있는지 확인해도 상관없지만 둘다 적어주었다.
			answer = new int[] {0, 0};
		} else {
			answer = new int[] {pqHigh.poll(), pqLow.poll()};
		}

		return answer;
	}
}
