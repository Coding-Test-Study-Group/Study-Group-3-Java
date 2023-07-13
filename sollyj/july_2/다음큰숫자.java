package sollyj.july_2;

public class 다음큰숫자 {
	public static void main(String[] args) {
		System.out.println(solution(78));
		System.out.println(solution(15));
	}

	private static int solution(int n) {
		int n1Cnt = binary1Cnt(n);

		int answer = n + 1;
		while (true) {
			int temp1Cnt = binary1Cnt(answer);

			if (n1Cnt == temp1Cnt) {
				break;
			}

			answer++;
		}

		return answer;
	}

	// 2진수의 1의 개수 반환하는 메서드
	private static int binary1Cnt(int n) {
		int count = 0;

		while (n != 0) {
			if (n % 2 != 0) {
				count++;
			}

			n /= 2;
		}

		return count;
	}
}
