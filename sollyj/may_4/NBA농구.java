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

				System.out.println(String.format("%02d:%02d", m, s));
			}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}