package sollyj.june_4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 게임 {
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			long X = Long.parseLong(st.nextToken());
			long Y = Long.parseLong(st.nextToken());

			long now = Y * 100 / X;    // 현재 승률
			int count;
			boolean flag = true;    // Z가 변하는지 확인하기 위한 flag

			if (now >= 99) {    // 99%와 100%는 확률이 변하지 않는다.
				flag = false;
			}

			// 이분탐색
			long start = 0;
			long end = X;    // 최대는 지금까지의 게임 횟수
			while (start <= end) {
				long mid = (start + end) / 2;

				long tempRate = (Y + mid) * 100 / (X + mid);

				if (tempRate > now) {    // 승률이 올랐다면 end를 줄여준다
					end = mid - 1;
				} else {    // 작거나 같다면 start를 늘려준다
					start = mid + 1;
				}
			}

			// while문을 끝내고 최솟값을 구하는 것이므로 start를 출력
			count = flag ? (int)start : -1;

			System.out.println(count);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
