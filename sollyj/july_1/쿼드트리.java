package sollyj.july_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 쿼드트리 {
	static StringBuilder sb = new StringBuilder();
	static int[][] video;

	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int N = Integer.parseInt(br.readLine());
			video = new int[N][N];

			// video 배열에 점 넣기
			// j가 x좌표, i가 y좌표 역할을 한다.
			for (int i = 0; i < N; i++) {
				String temp = br.readLine();
				for (int j = 0; j < N; j++) {
					video[i][j] = temp.charAt(j) - '0';
				}
			}

			quadTree(0, 0, N);

			System.out.println(sb.toString());
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	// 배열의 x좌표, y좌표, 영상 크기
	private static void quadTree(int x, int y, int size) {
		if (sameColor(x, y, size)) {    // 현재 구역이 다 같은색이면 그 색을 넣어준다.
			sb.append(video[y][x]);
		} else {    // 색이 다르면
			int halfSize = size / 2;    // 반으로 나눠주고

			// 왼쪽 위 -> 오른쪽 위 -> 왼쪽 아래 -> 오른쪽 아래 순서대로 재귀를 해주고 괄호안에 넣어준다.
			sb.append("(");
			quadTree(x, y, halfSize);    // 왼쪽 위
			quadTree(x + halfSize, y, halfSize);    // 오른쪽 위
			quadTree(x, y + halfSize, halfSize);    // 왼쪽 아래
			quadTree(x + halfSize, y + halfSize, halfSize);    // 오른쪽 아래
			sb.append(")");
		}
	}

	private static boolean sameColor(int x, int y, int size) {
		int dot = video[y][x];

		for (int i = y; i < y + size; i++) {
			for (int j = x; j < x + size; j++) {
				if (dot != video[i][j])
					return false;
			}
		}

		return true;
	}
}
