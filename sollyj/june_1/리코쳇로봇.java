// Programmers_리코쳇로봇
package sollyj.june_1;

import java.util.LinkedList;
import java.util.Queue;

public class 리코쳇로봇 {
	static char[][] map;    // 주어진 board를 2차원 char 배열로 변환
	static int H, W;    // board의 세로, 가로 길이
	static boolean[][] visited;

	public static void main(String[] args) {
		String[] board = {"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."};
		System.out.println(solution(board));

		// String[] board = {".D.R", "....", ".G..", "...D"};
		// System.out.println(solution(board));
	}

	private static int solution(String[] board) {
		H = board.length;
		W = board[0].length();

		map = new char[H][W];

		Moving robot = null;
		Moving goal = null;

		// robot, goal 위치 찾고
		// map 설정해주기
		for (int h = 0; h < H; h++) {
			for (int w = 0; w < W; w++) {
				char c = board[h].charAt(w);

				if (c == 'R') {
					robot = new Moving(h, w, 0);
				} else if (c == 'G') {
					goal = new Moving(h, w, 0);
				}

				map[h][w] = c;
			}
		}

		visited = new boolean[H][W];

		int answer = bfs(map, robot, goal);

		return answer;
	}

	private static int bfs(char[][] map, Moving robot, Moving goal) {
		Queue<Moving> queue = new LinkedList<>();

		visited[robot.h][robot.w] = true;
		queue.add(robot);

		// 상, 하, 좌, 우로 탐색하기 위한 배열
		int[] searchW = {0, 0, -1, 1};
		int[] searchH = {-1, 1, 0, 0};

		while (!queue.isEmpty()) {
			Moving node = queue.poll();

			// 목표 도착했으면 끝
			if (node.h == goal.h && node.w == goal.w) {
				return node.breadth;
			}

			// 상, 하, 좌, 우 탐색
			for (int i = 0; i < 4; i++) {
				int newW = node.w + searchW[i];
				int newH = node.h + searchH[i];

				// 벽에 부딪히거나, 장애물에 부딪힐때까지 반복
				while (inRange(newH, newW) && map[newH][newW] != 'D') {
					newW += searchW[i];
					newH += searchH[i];
				}

				// 부딪히기 직전 좌표
				newW -= searchW[i];
				newH -= searchH[i];

				if (!visited[newH][newW]) {
					queue.add(new Moving(newH, newW, node.breadth + 1));    // 이동횟수 증가하여 큐에 추가
					visited[newH][newW] = true;
				}
			}
		}

		return -1;
	}

	private static boolean inRange(int h, int w) {
		return (h >= 0 && w >= 0 && h < H && w < W);
	}
}

// 위치좌표와 몇번 이동했는지 정보를 담고있는 클래스
class Moving {
	int h, w, breadth;

	public Moving(int h, int w, int breadth) {
		this.h = h;
		this.w = w;
		this.breadth = breadth;
	}
}
