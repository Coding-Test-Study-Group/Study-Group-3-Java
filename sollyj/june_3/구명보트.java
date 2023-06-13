package sollyj.june_3;

import java.util.Arrays;

public class 구명보트 {
	public static void main(String[] args) {
		//int[] people = {70, 50, 80, 50};
		int[] people = {70, 80, 50};

		int limit = 100;

		System.out.println(solution(people, limit));
	}

	private static int solution(int[] people, int limit) {
		int answer = 0;

		Arrays.sort(people);

		int left = 0;
		int right = people.length - 1;

		while (left <= right) {
			int weight = people[left] + people[right];

			if (weight <= limit) {    // 제한을 넘지 않으면
				answer++;    // 정답 카운트하고
				right--;    // 두명 다 탔단 뜻이므로 rigth--, left++
				left++;
			} else {    // 제한을 넘으면 어차피 무거운 사람 한명은 혼자 타고 보내야 하므로 정답 카운트하고
				answer++;
				right--;    // right를 --
			}
		}

		return answer;
	}
}
