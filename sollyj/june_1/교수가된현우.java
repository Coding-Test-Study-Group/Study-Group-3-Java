// Baekjoon_3474
package sollyj.june_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 교수가된현우 {
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int T = Integer.parseInt(br.readLine());

			for (int i = 0; i < T; i++) {
				int count = 0;

				int N = Integer.parseInt(br.readLine());

				for (int j = 5; j <= N; j *= 5) {
					count += N / j;
				}

				System.out.println(count);
			}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
