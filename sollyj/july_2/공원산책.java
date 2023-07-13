package sollyj.july_2;

import java.util.Arrays;

public class 공원산책 {
	public static void main(String[] args) {
		String[] park1 = {"SOO", "OOO", "OOO"};
		String[] routes1 = {"E 2", "S 2", "W 1"};
		String[] park2 = {"SOO", "OXX", "OOO"};
		String[] routes2 = {"E 2", "S 2", "W 1"};
		String[] park3 = {"OSO", "OOO", "OXO", "OOO"};
		String[] routes3 = {"E 2", "S 3", "W 1"};

		System.out.println(Arrays.toString(solution(park1, routes1)));
		System.out.println(Arrays.toString(solution(park2, routes2)));
		System.out.println(Arrays.toString(solution(park3, routes3)));
	}

	private static int[] solution(String[] park, String[] routes) {
		int[] answer = {};

		int h = park.length;    // map의 세로 길이
		int w = park[0].length();    // map의 가로 길이
		char[][] map = new char[h][w];

		// park를 2차원 char 배열로 바꾸기
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				char c = park[i].charAt(j);

				map[i][j] = c;

				if (c == 'S') {
					// 현재 위치 넣기
					answer = new int[] {i, j};
				}
			}
		}

		// 공원 산책 명령 수행
		for (String route : routes) {
			String direction = route.split(" ")[0];
			int move = Integer.parseInt(route.split(" ")[1]);

			int new_h = answer[0];
			int new_w = answer[1];

			for (int m = 1; m <= move; m++) {
				switch (direction) {
					case "E":
						new_w++;
						break;
					case "W":
						new_w--;
						break;
					case "S":
						new_h++;
						break;
					case "N":
						new_h--;
						break;
				}

				// 공원 안 벗어나는지
				if (new_h >= 0 && new_w >= 0 && new_h < h && new_w < w) {
					// 장애물이면 이동 그만하고 해당 명령은 무효
					if (map[new_h][new_w] == 'X')
						break;

					// 다 이동하는 동안 좌표가 범위내이고, 장애물 만난 적이 없으면 새로 갱신된 위치 넣기
					if (m == move)
						answer = new int[] {new_h, new_w};
				}
			}
		}

		return answer;
	}
}
