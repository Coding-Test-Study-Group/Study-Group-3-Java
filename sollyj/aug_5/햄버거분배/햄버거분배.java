package sollyj.aug_5.햄버거분배;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 햄버거분배 {
	public static void main(String[] args) {
		try {
			System.setIn(new FileInputStream("sollyj/aug_5/햄버거분배/input.txt"));
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			List<Character> table = new ArrayList<>();
			String str = br.readLine();
			for (int i = 0; i < N; i++) {
				table.add(str.charAt(i));
			}

			int answer = 0;

			for (int i = 0; i < N; i++) {
				// 사람 주변의 거리 K 이하부터 왼쪽부터 탐색할 것이다.
				// 왼쪽부터 탐색하는 이유는 그 다음 사람이 햄버거를 먹을 확률을 높이려면 왼쪽부터 해치워야 하기때문
				// 이때 인덱스를 넘어가지 않게 인덱스 유효성 검사 해주기
				if (table.get(i) == 'P') {
					int start = Math.max(i - K, 0);
					int end = Math.min(i + K, N - 1);

					for (int j = start; j <= end; j++) {
						if (table.get(j) == 'H') {
							table.set(j, 'h');    // 먹은 햄버거는 소문자로 바꿔준다.
							answer++;
							break;
						}
					}
				}
			}

			System.out.println(answer);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
