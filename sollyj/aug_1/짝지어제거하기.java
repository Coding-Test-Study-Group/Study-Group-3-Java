package sollyj.aug_1;

import java.util.Stack;

public class 짝지어제거하기 {
	public static void main(String[] args) {
		System.out.println(solution("baabaa"));
		System.out.println(solution("cdcd"));
	}

	private static int solution(String s) {
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < s.length(); i++) {
			if (stack.isEmpty() || stack.peek() != s.charAt(i)) {    // 스택이 비어있거나 앞 문자가 같은 알파벳이 아니면 스택에 문자를 넣어준다.
				stack.push(s.charAt(i));
			} else if (stack.peek() == s.charAt(i)) {    // 같은 알파벳이 2개 연속 붙어있다는 뜻
				stack.pop();
			}
		}

		// 연산을 끝냈을때 스택이 비어있다는 것은 제거하기 성공
		if (stack.isEmpty()) {
			return 1;
		} else {
			return 0;
		}
	}
}
