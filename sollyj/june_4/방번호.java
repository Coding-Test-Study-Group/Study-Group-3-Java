package sollyj.june_4;

import java.util.Scanner;

public class 방번호 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String roomNum = scan.nextLine();

		// 0~9숫자 한 세트를 의미한다.
		int[] set = new int[10];

		int answer = 0;

		for (int i = 0; i < roomNum.length(); i++) {
			int num = roomNum.charAt(i) - '0';

			// num에 해당하는 인덱스 값을 ++해주며 몇번 나왔는지 센다.
			// 6, 9는 따로 예외 처리
			if (num == 6 || num == 9) {
				set[6]++;
			} else {
				set[num]++;
			}
		}

		// 6, 9는 한 세트 내에서 쓸 수 있다. set[6]에 모두 카운트 해줬으므로, 나누기 2를 해준다.
		if (set[6] % 2 == 0) {
			set[6] /= 2;
		} else {    // 홀수라는 것은 세트 하나 더 썼다는 거
			set[6] = set[6] / 2 + 1;
		}

		// 배열 set를 순회하면서 최댓값을 찾는다.
		for (int i = 0; i <= 9; i++) {
			if (set[i] > answer) {
				answer = set[i];
			}
		}

		System.out.println(answer);
	}
}
