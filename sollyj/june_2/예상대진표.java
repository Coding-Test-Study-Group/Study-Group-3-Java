// Programmers_예상대진표
package sollyj.june_2;

public class 예상대진표 {
	public static void main(String[] args) {
		System.out.println(solution(8, 4, 7));
	}

	private static int solution(int n, int a, int b) {
		int answer = 0;

		while (true) {
			if (a == b)
				break;

			a = (a % 2) == 0 ? a / 2 : a / 2 + 1;
			b = (b % 2) == 0 ? b / 2 : b / 2 + 1;
			
			answer++;
		}

		return answer;
	}
}
