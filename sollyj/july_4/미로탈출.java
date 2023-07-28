package sollyj.july_4;

import java.util.LinkedList;
import java.util.Queue;

public class 미로탈출 {
	static char[][] miro;
	static int height, width;
	static boolean[][] visited;

	public static void main(String[] args) {
		System.out.println(solution(new String[] {"SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE"}));
		System.out.println(solution(new String[] {"LOOXS", "OOOOX", "OOOOO", "OOOOO", "EOOOO"}));
	}

	private static int solution(String[] maps) {
		int answer = 0;
		height = maps.length;
		width = maps[0].length();

		Pos start = new Pos();
		Pos lever = new Pos();
		Pos exit = new Pos();

		// 미로 초기화
		miro = new char[height][width];

		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				miro[h][w] = maps[h].charAt(w);

				if (miro[h][w] == 'S') {
					start = new Pos(h, w, 0);
				} else if (miro[h][w] == 'L') {
					lever = new Pos(h, w, 0);
				} else if (miro[h][w] == 'E') {
					exit = new Pos(h, w, 0);
				}
			}
		}

		if (bfs(start, lever) == -1 || bfs(lever, exit) == -1) {
			return -1;
		}

		answer = bfs(start, lever) + bfs(lever, exit);

		return answer;
	}

	private static int bfs(Pos start, Pos end) {
		Queue<Pos> queue = new LinkedList<>();

		// 방문 기록 배열 초기화
		visited = new boolean[height][width];

		int[] searchW = {0, 0, -1, 1};
		int[] searchH = {-1, 1, 0, 0};

		visited[start.h][start.w] = true;
		queue.add(start);

		while (!queue.isEmpty()) {
			Pos now = queue.poll();

			// end까지 다 탐색했다면 count을 반환
			if (now.h == end.h && now.w == end.w) {
				return now.count;
			}

			// 상하좌우 인접 탐색
			for (int i = 0; i < 4; i++) {
				int newW = now.w + searchW[i];
				int newH = now.h + searchH[i];

				if (newW >= 0 && newH >= 0 && newW < width && newH < height) {    // 미로 안에 있으면서
					if (!visited[newH][newW] && miro[newH][newW] != 'X') {    // 방문하지 않았고, 벽이 아니라면
						Pos newPos = new Pos(newH, newW, now.count + 1);    // 시간 카운트+1한 새로운 좌표

						visited[newH][newW] = true;
						queue.add(newPos);
					}
				}
			}
		}

		return -1;
	}
}

class Pos {
	int h, w, count;    // 위치정보와 시간카운트 변수

	public Pos() {
	}

	public Pos(int h, int w, int count) {
		this.h = h;
		this.w = w;
		this.count = count;
	}
}
