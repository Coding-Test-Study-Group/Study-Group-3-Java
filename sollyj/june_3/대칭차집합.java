// Baekjoon_1269_대칭차집합
package sollyj.june_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 대칭차집합 {
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			StringTokenizer st;

			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			int[] A_nums = new int[A];
			int intersection = 0;    // 교집합 개수

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < A; i++) {
				A_nums[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(A_nums);    // 오름차순 정렬

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < B; i++) {
				int B_num = Integer.parseInt(st.nextToken());

				// 이분탐색으로 교집합 있는지 찾기
				int start = 0;
				int end = A - 1;

				while (start <= end) {
					int mid = (start + end) / 2;

					if (A_nums[mid] > B_num) {
						end = mid - 1;
					} else if (A_nums[mid] < B_num) {
						start = mid + 1;
					} else {
						intersection++;
						break;
					}
				}
			}

			// 대칭차집합 원소 개수 = A원소 + B원소 - (2 * 교집합개수)
			System.out.println(A + B - (2 * intersection));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
