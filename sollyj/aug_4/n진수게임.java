package sollyj.aug_4;

public class n진수게임 {
	public static void main(String[] args) {
		System.out.println(solution(2, 4, 2, 1));
		System.out.println(solution(16, 16, 2, 1));
		System.out.println(solution(16, 16, 2, 2));
	}

	private static String solution(int n, int t, int m, int p) {
		StringBuilder all = new StringBuilder();    // 게임 전체에 나온 숫자
		StringBuilder answer = new StringBuilder();    // 튜브꺼

		int last = p + m * (t - 1);    // 튜브의 마지막 순서
		int num = 0;

		while (all.length() <= last) {
			all.append(Integer.toString(num++, n));    // num을 n진수로 변환해주는 함수
		}
		
		// 튜브꺼 구하는 과정
		// p번부터 last까지 m을 더해주면서 반복 (인덱스이기 때문에 p-1부터 시작한다)
		for (int i = p - 1; i < last; i = i + m) {
			answer.append(all.charAt(i));
		}

		return answer.toString().toUpperCase();
	}
}
