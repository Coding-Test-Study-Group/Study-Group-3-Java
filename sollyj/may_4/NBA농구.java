// Baekjoon_2852
package sollyj.may_4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NBA농구 {
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			StringTokenizer st;

			int[] ans_time = new int[2];    // 이기고 있던 시간 (초로 바꾸어 저장)
			int[] score = {0, 0};    // 점수
			int goalTeam;    // 이긴팀
			String goalTime;    // 득점 시간
			int N = Integer.parseInt(br.readLine());    // 골 횟수

			// 처음 이긴 팀과 시간 저장
			st = new StringTokenizer(br.readLine());
			goalTeam = Integer.parseInt(st.nextToken());    // 처음 이긴팀
			goalTime = st.nextToken();    // 처음 이긴 팀의 득점 시간
			score[goalTeam - 1]++;

			// 시간 계산하기 위한 변수들
			// 초로 바꾸어 저장함
			int start_time = Integer.parseInt(goalTime.split(":")[0]) * 60 + Integer.parseInt(goalTime.split(":")[1]);
			int end_time;

			for (int n = 1; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				goalTeam = Integer.parseInt(st.nextToken());
				goalTime = st.nextToken();

				end_time = Integer.parseInt(goalTime.split(":")[0]) * 60 + Integer.parseInt(goalTime.split(":")[1]);

				if (score[0] < score[1]) {    // 2팀이 이기고 있으면
					ans_time[1] += end_time - start_time;
				} else if (score[0] > score[1]) {    // 1팀이 이기고 있으면
					ans_time[0] += end_time - start_time;
				}

				score[goalTeam - 1]++;

				start_time = end_time;
			}

			// 마지막 골 이후 계산
			if (score[0] < score[1]) {    // 2팀이 이기고 있으면
				ans_time[1] += 48 * 60 - start_time;
			} else if (score[0] > score[1]) {    // 1팀이 이기고 있으면
				ans_time[0] += 48 * 60 - start_time;
			}

			// 정답 출력
			for (int i = 0; i < 2; i++) {
				int m = ans_time[i] / 60;    // 분
				int s = ans_time[i] % 60;    // 초
				String minStr;
				String secStr;

				if (m < 10) {
					minStr = "0" + m;
				} else {
					minStr = String.valueOf(m);
				}

				if (s < 10) {
					secStr = "0" + s;
				} else {
					secStr = String.valueOf(s);
				}

				System.out.println(minStr + ":" + secStr);
			}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	/*
	// 1팀 이기고 있던 시간 구하는 함수
	private static void oneLeadTime(int start_min, int start_sec, int end_min, int end_sec) {
		// 소요 시간
		int temp_min = end_min - start_min;
		int temp_sec = end_sec - start_sec;

		if (temp_sec < 0) {
			temp_sec += 60;
			temp_min -= 1;
		}

		// 소요 시간을 원래 이기고 있던 시간에 더해주기
		if (sec[0] + temp_sec >= 60) {
			min[0] = min[0] + (sec[0] + temp_sec) / 60;
			sec[0] = (sec[0] + temp_sec) % 60;
		} else {
			min[0] += temp_min;
			sec[0] += temp_sec;
		}
	}

	// 2팀 이기고 있던 시간 구하는 함수
	private static void twoLeadTime(int start_min, int start_sec, int end_min, int end_sec) {
		// 소요 시간
		int temp_min = end_min - start_min;
		int temp_sec = end_sec - start_sec;

		if (temp_sec < 0) {
			temp_sec += 60;
			temp_min -= 1;
		}

		// 소요 시간을 원래 이기고 있던 시간에 더해주기
		if (sec[1] + temp_sec >= 60) {
			min[1] = min[1] + (sec[1] + temp_sec) / 60;
			sec[1] = (sec[1] + temp_sec) % 60;
		} else {
			min[1] += temp_min;
			sec[1] += temp_sec;
		}
	}
	 */
}
