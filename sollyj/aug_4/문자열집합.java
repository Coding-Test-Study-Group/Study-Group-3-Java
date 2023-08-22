package sollyj.aug_4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class 문자열집합 {
	public static void main(String[] args) {
		try {
			System.setIn(new FileInputStream("sollyj/aug_4/input.txt"));
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());    // 집합 S의 원소 개수
			int M = Integer.parseInt(st.nextToken());    // 검사해야하는 문자열 개수

			// 집합 S에 원소 넣기
			HashSet<String> S = new HashSet<>();
			for (int i = 0; i < N; i++) {
				S.add(br.readLine());
			}

			int answer = 0;
			for (int i = 0; i < M; i++) {
				String str = br.readLine();

				if (S.contains(str))
					answer++;
			}

			System.out.println(answer);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
