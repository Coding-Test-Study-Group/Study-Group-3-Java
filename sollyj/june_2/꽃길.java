// Baekjoon_14620_꽃길
package sollyj.june_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 꽃길 {
	static int N;
	static int[][] garden;
	static boolean[][] visited;
	static int answer;

	static int[] searchW = {-1, 0, 1, 0};
	static int[] searchH = {0, 1, 0, -1};

	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			N = Integer.parseInt(br.readLine());
			garden = new int[N][N];
			visited = new boolean[N][N];
			answer = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());

				for (int j = 0; j < N; j++) {
					garden[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			dfs(0, 0);

			System.out.println(answer);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	private static void dfs(int count, int cost) {
		if (count == 3) {
			answer = Math.min(cost, answer);
		} else {
			for (int i = 1; i < N - 1; i++) {
				for (int j = 1; j < N - 1; j++) {
					if (!visited[i][j] && isPossible(i, j)) {
						visited[i][j] = true;
						int sum = sum(i, j);

						dfs(count + 1, cost + sum);

						// 재귀로 꽃 3개의 비용계산을 마친 후엔 방문 해제 해주어 다시 꽃 3개 비용 계산 시작
						visitClear(i, j);
					}
				}
			}
		}
	}

	private static boolean isPossible(int h, int w) {
		for (int i = 0; i < 4; i++) {
			int nh = h + searchH[i];
			int nw = w + searchW[i];

			if (visited[nh][nw])
				return false;
		}

		return true;
	}

	private static int sum(int h, int w) {
		int sum = garden[h][w];

		for (int i = 0; i < 4; i++) {
			int nh = h + searchH[i];
			int nw = w + searchW[i];

			sum += garden[nh][nw];
			visited[nh][nw] = true;
		}

		return sum;
	}

	private static void visitClear(int h, int w) {
		visited[h][w] = false;

		for (int i = 0; i < 4; i++) {
			int nh = h + searchH[i];
			int nw = w + searchW[i];

			visited[nh][nw] = false;
		}
	}
}
