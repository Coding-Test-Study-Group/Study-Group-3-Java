package sollyj.aug_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 예산 {
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int N = Integer.parseInt(br.readLine());
			int[] request = new int[N];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				request[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(request);    // 오름차순 정렬

			int budget = Integer.parseInt(br.readLine());

			// 이분탐색
			int start = 1;
			int end = request[N - 1];
			int limit = 0;    // 상한액
			while (start <= end) {
				int temp_limit = (start + end) / 2;
				int sum = 0;

				for (int i = 0; i < N; i++) {
					if (request[i] <= temp_limit) {    // 예산요청이 상한액보다 작거나 같으면 그대로 더한다
						sum += request[i];
					} else {    // 예산요청이 상한액보다 크면 상한액을 더한다
						sum += temp_limit;
					}
				}

				if (sum > budget)
					end--;
				else {    // 합이 총 예산보다 작거나 같으면 일단 limit에 저장해두고 상한액을 늘려서 다시 탐색 해본다.
					limit = temp_limit;
					start++;
				}
			}

			System.out.println(limit);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
