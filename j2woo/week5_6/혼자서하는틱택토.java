package j2woo.week5_6;

public class 혼자서하는틱택토 {
    public static char [][] map;
    public int solution(String[] board) {
        int answer = 1;
        map = new char[3][3];
        int OCnt = 0;
        int XCnt = 0;
        // X와 O의 개수 체크
        for (int i = 0; i < 3; i++) {
            // map에 값 넣기
            map[i] = board[i].toCharArray();
            for (int j = 0; j < 3; j++) {
                if (map[i][j] == 'O') OCnt++;
                else if (map[i][j] == 'X') XCnt++;
            }
        }

        // O - X가 1이나 0이어야 한다.
        if (OCnt - XCnt != 0 && OCnt -XCnt != 1) return 0;

        int OWinCnt = findWinCnt('O');
        int XWinCnt = findWinCnt('X');

        // 둘 다 이겼을 경우는 안된다.
        if (OWinCnt > 0 && XWinCnt > 0) return 0;

        // O가 이겼을 경우, O가 X보다 하나 많아야한다.
        if (OWinCnt > 0 ) {
            if (OCnt - XCnt != 1) return 0;
        }

        // X가 이겼을 경우, O와 X 수가 같아야한다.
        if (XWinCnt > 0) {
            if (OCnt - XCnt != 0) return 0;
        }


        return answer;
    }

    // 문자가 빙고한 개수
    public int findWinCnt (char c) {
        int cnt = 0;
        // 가로, 세로 체크
        for (int i = 0; i < 3; i++) {
            // 가로
            if (map[i][0] == c && map[i][1] == c && map[i][2] == c) cnt++;
            if (map[0][i] == c && map[1][i] == c && map[2][i] == c) cnt++;
        }

        // 대각선 체크
        if(map[0][0] == c && map[1][1] == c && map[2][2] == c)
            cnt++;
        if(map[0][2] == c && map[1][1] == c && map[2][0] == c)
            cnt++;

        return cnt;
    }
}
