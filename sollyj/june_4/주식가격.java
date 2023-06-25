package sollyj.june_4;

import java.util.Arrays;
import java.util.Stack;

public class 주식가격 {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[] {1, 2, 3, 2, 3})));
	}

	private static int[] solution(int[] prices) {
		int len = prices.length;

		int[] answer = new int[len];

		Stack<Integer> stack = new Stack<>();

		// 이 for문은 answer 배열에 값을 할당하기 위한 반복문으로 answer 길이 만큼 돈다.
		for (int i = 0; i < len; i++) {
			int price = prices[i];
			stack.push(0);    // 처음에 일단 0으로 초기화

			// 이 for문은 answer[i]에 들어갈 값 계산
			// stack을 이용하여 가격이 떨어지지 않은 기간을 누적 더해준다.
			for (int j = i + 1; j < len; j++) {
				if (price > prices[j]) {    // 가격이 떨어졌다면 +1 해준 후 answer[i]에 넣어준다.
					answer[i] = stack.pop() + 1;
					break;
				} else {    // 가격이 떨어지지 않았다면 pop 하여 +1 해준 후 다시 push
					stack.push(stack.pop() + 1);
				}
			}

			// 가격이 끝까지 안떨어졌다면 stack에 값이 있을 것이다. 이것을 answer[i]에 넣어준다.
			// 가격이 이전에 떨어졌다면 이미 위의 if문에서 answer[i]에 넣어주었다.
			if (!stack.isEmpty())
				answer[i] = stack.pop();
		}

		return answer;
	}
}
