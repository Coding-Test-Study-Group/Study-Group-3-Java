package sollyj.aug_2;

public class 숫자의표현 {
	public static void main(String[] args) {
		System.out.println(solution(15));
	}

	private static int solution(int n) {
		int answer = 1;

		for (int i = 1; i <= n / 2; i++) {
			int start = i;
			int sum = start;

			while (sum < n) {
				sum += ++start;
			}

			if (sum == n)
				answer++;
		}
		
		return answer;
	}
}
