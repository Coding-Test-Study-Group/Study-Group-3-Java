package sollyj.aug_2;

public class k진수에서_소수개수_구하기 {
	static int answer;

	public static void main(String[] args) {
		System.out.println(solution(437674, 3));
		System.out.println(solution(110011, 10));
	}

	private static int solution(int n, int k) {
		answer = 0;

		String k진수 = "";

		if (k == 10) {
			k진수 = String.valueOf(n);
		} else {
			while (n / k >= 0) {
				k진수 = String.valueOf(n % k).concat(k진수);

				if (n / k == 0)
					break;

				n /= k;
			}
		}

		countPrime(k진수);

		return answer;
	}

	private static void countPrime(String k진수) {
		String[] strNums = k진수.split("0");

		for (String strNum : strNums) {
			if (strNum.length() == 0)
				continue;

			long num = Long.parseLong(strNum);
			boolean flag = true;    // 소수 판별 위해

			// 소수 판별
			if (num == 1)
				flag = false;

			// num을 2부터 num까지 나눠보고 나머지가 0이 안나오면 소수
			for (int n = 2; n <= Math.sqrt(num); n++) {
				if (num % n == 0) {
					flag = false;
					break;
				}
			}

			answer = flag ? answer + 1 : answer;
		}
	}
}
