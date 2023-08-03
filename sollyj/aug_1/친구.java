package sollyj.aug_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 친구 {
	static int N;    // 사람의 수
	static String[] friendsList;

	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			N = Integer.parseInt(br.readLine());

			friendsList = new String[N];

			for (int i = 0; i < N; i++) {
				friendsList[i] = br.readLine();
			}

			System.out.println(find2FriendNum());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static int find2FriendNum() {
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			int sum = 0;

			for (int j = 0; j < N; j++) {
				if (i == j)
					continue;

				char temp = friendsList[i].charAt(j);

				if (temp == 'Y') {    // 일단 친구 개수만큼 카운트
					sum++;
				} else if (is2Friend(i, j)) {    //	i는 현재 탐색하고 있는 사람, j는 다른 사람들
					sum++;
				}
			}

			if (max < sum)
				max = sum;
		}

		return max;
	}

	private static boolean is2Friend(int a, int b) {
		// a와 친구이고, b와 친구인 c가 존재하는지
		for (int c = 0; c < N; c++) {
			char temp1 = friendsList[c].charAt(b);

			if (temp1 == 'Y') {
				char temp2 = friendsList[a].charAt(c);

				if (temp2 == 'Y')
					return true;
			}
		}

		return false;
	}
}
