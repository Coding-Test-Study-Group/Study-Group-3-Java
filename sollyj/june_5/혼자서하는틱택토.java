package sollyj.june_5;

public class 혼자서하는틱택토 {
	public static void main(String[] args) {
		System.out.println(solution(new String[] {"O.X", ".O.", "..X"}));
		System.out.println(solution(new String[] {"OOO", "...", "XXX"}));
		System.out.println(solution(new String[] {"...", ".X.", "..."}));
		System.out.println(solution(new String[] {"...", "...", "..."}));
	}

	private static int solution(String[] board) {
		char[][] tictactoe = new char[3][3];    // 게임판
		int oCnt = 0;
		int xCnt = 0;

		// 게임판을 초기화 해주면서 O, X 카운트를 센다.
		for (int i = 0; i < 9; i++) {
			tictactoe[i / 3][i % 3] = board[i / 3].charAt(i % 3);

			if (tictactoe[i / 3][i % 3] == 'O') {
				oCnt++;
			} else if (tictactoe[i / 3][i % 3] == 'X') {
				xCnt++;
			}
		}

		// X가 O보다 많을 수 없다.
		if (xCnt > oCnt)
			return 0;
		// O와 X개수가 2개 이상 차이 날 수 없다.
		if (oCnt > xCnt + 1)
			return 0;
		// O, X 둘 다 한 줄을 완성하는 일은 없다.
		if (isWin('O', tictactoe) && isWin('X', tictactoe))
			return 0;
		// O가 이겼을때, X는 무조건 O보다 1개 적어야 한다.
		if (isWin('O', tictactoe) && xCnt != oCnt - 1)
			return 0;
		// X가 이겼을때, O개수는 X와 같아야 한다.
		if (isWin('X', tictactoe) && xCnt != oCnt) {
			return 0;
		}

		// 위의 조건문에서 걸러지지 않았다면 규칙을 지킨 것
		return 1;
	}

	// target의 이김 여부 반환 메서드
	private static boolean isWin(char target, char[][] tictactoe) {
		// 가로
		for (int h = 0; h < 3; h++) {
			if (tictactoe[h][0] == target && tictactoe[h][1] == target && tictactoe[h][2] == target) {
				return true;
			}
		}

		// 세로
		for (int v = 0; v < 3; v++) {
			if (tictactoe[0][v] == target && tictactoe[1][v] == target && tictactoe[2][v] == target) {
				return true;
			}
		}

		// 대각선
		if (tictactoe[0][0] == target && tictactoe[1][1] == target && tictactoe[2][2] == target) {
			return true;
		}

		if (tictactoe[2][0] == target && tictactoe[1][1] == target && tictactoe[0][2] == target) {
			return true;
		}

		return false;
	}
}
